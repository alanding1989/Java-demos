package concurrent

import (
    "fmt"
    "time"
)

// <editor-fold desc="@ Goroutine">
func iter(from string) {
    for i := 0; i < 3; i++ {
        fmt.Println(from, ":", i)
        time.Sleep(0.5e9)
    }
}

func Goroutine() {
    // runtime.GOMAXPROCS(4)
    iter("direct call func and block")
    fmt.Println("sleep for 2s\n")
    time.Sleep(2e9)

    go iter("goroutine 1 start and count")

    go func(msg string) {
        for i := 0; i < 3; i++ {
            fmt.Println(msg, ":", i)
            time.Sleep(0.5e9)
        }
    }("goroutine 2 start and count")

    fmt.Println("main again, sleep for 2s\n")
    time.Sleep(2e9)
    fmt.Println("main routine end")
}

// </editor-fold>

func Channel() {
    job := make(chan string)

    // send and receive all will block current goroutine
    go func() {
        job <- "ping from routine 1"
    }()

    msg := <-job

    fmt.Println("main routine receive :", msg)
}

func SyncChannel() {
    doneSignal := make(chan bool)

    go func(signal chan bool) {
        fmt.Print("working...")
        time.Sleep(time.Second)
        fmt.Println("done")

        signal <- true
    }(doneSignal)

    <-doneSignal
}

// <editor-fold desc="@ direction of channel">
func DirectionOfChannel() {
    pings := make(chan string, 1)
    pongs := make(chan string, 1)
    ping(pings, "passed message")
    pong(pings, pongs)
    fmt.Println(<-pongs)
}

func ping(pings chan<- string, msg string) {
    pings <- msg
}

func pong(pings <-chan string, pongs chan<- string) {
    msg := <-pings
    pongs <- msg
}

// </editor-fold>

func SelectBlockChan() {
    c1 := make(chan string)
    c2 := make(chan string)

    go func() {
        time.Sleep(time.Second * 1)
        c1 <- "one"
    }()

    go func() {
        time.Sleep(time.Second * 2)
        c2 <- "two"
    }()

    for i := 0; i < 2; i++ {
        select {
        // receive will block current goroutine
        case msg1 := <-c1:
            fmt.Println("received", msg1)
        case msg2 := <-c2:
            fmt.Println("received", msg2)
        }
    }
}

func TimeoutChan() {
    c1 := make(chan string, 1)
    go func() {
        time.Sleep(time.Second * 2)
        c1 <- "result 1"
    }()
    // @  by default, select will handle the first msg received
    select {
    case res := <-c1:
        fmt.Println(res)
    // delay 1 second and than send the msg
    case <-time.After(time.Second * 1):
        fmt.Println("timeout 1")
    }

    c2 := make(chan string, 1)
    go func() {
        time.Sleep(time.Second * 2)
        c2 <- "result 2"
    }()
    select {
    case res := <-c2:
        fmt.Println(res)
    case <-time.After(time.Second * 3):
        fmt.Println("timeout 2")
    }
}

func NonBlockChan() {
    messages := make(chan string)
    signals := make(chan bool)

    select {
    // with default branch, receive won`t block current goroutine
    case msg := <-messages:
        fmt.Println("received messages", msg)
    default:
        fmt.Println("no message received")
    }

    msg := "hi"
    select {
    case messages <- msg:
        fmt.Println("sent messages", msg)
    default:
        fmt.Println("no message sent")
    }

    select {
    case msg := <-messages:
        fmt.Println("received message", msg)
    case sig := <-signals:
        fmt.Println("received signal", sig)
    default:
        fmt.Println("no activity")
    }
}

func CloseChan() {
    jobs := make(chan int, 5)
    done := make(chan bool)

    go func() {
        for {
            j, more := <-jobs
            if more {
                fmt.Println("received job", j)
            } else {
                fmt.Println("received all jobs")
                done <- true
                return
            }
        }
    }()

    for j := 0; j < 3; j++ {
        jobs <- j
        fmt.Println("sent job", j)
    }
    close(jobs)
    fmt.Println("sent all jobs")

    <-done
}

func IterChan() {
    queue := make(chan string, 2)
    queue <- "one"
    queue <- "two"
    // close(queue)

    for elem := range queue {
        fmt.Println(elem)
    }
}

func Rate_limiting() {
    requests := make(chan int, 5)
    for i := 0; i < 5; i++ {
        requests <- i
    }
    close(requests)

    // speed limit: every 200 ms receive a request
    limiter := time.Tick(time.Millisecond * 200)
    for req := range requests {
        <-limiter
        fmt.Println("request", req, time.Now())
    }

    burstyLimiter := make(chan time.Time, 3)
    for i := 0; i < 3; i++ {
        burstyLimiter <- time.Now()
    }

    go func() {
        for t := range time.Tick(time.Millisecond * 200) {
            burstyLimiter <- t
        }
    }()

    burstyRequests := make(chan int, 5)
    for i := 0; i < 5; i++ {
        burstyRequests <- i
    }
    close(burstyRequests)
    for req := range burstyRequests {
        <-burstyLimiter
        fmt.Println("request", req, time.Now())
    }
}
