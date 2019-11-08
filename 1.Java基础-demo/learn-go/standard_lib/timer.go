package standard_lib

import (
    "fmt"
    "time"
)

func TimerExp() {
    timer1 := time.NewTimer(time.Second * 2)
    // <-timer1.C
    // fmt.Println("Timer 1 expired")

    // if !timer1.Stop() {
    //     <-timer1.C
    // }
    timer1.Reset(time.Second * 3)
    go func() {
        <-timer1.C
        fmt.Println("Timer 1 reset expired")
    }()

    time.Sleep(time.Second * 4)
    // timer2 := time.NewTimer(time.Second * 2)
    // stop2 := timer2.Stop()
    // if stop2 {
    //     fmt.Println("Timer 2 stopped")
    // }
}

func TickerExp() {
    // repeat an action in a specific time duration
    ticker := time.NewTicker(time.Millisecond * 500)
    go func() {
        for t := range ticker.C {
            fmt.Println("Tick at", t)
        }
    }()

    time.Sleep(time.Millisecond * 1600)
    ticker.Stop()
    fmt.Println("Ticker stopped")
}

func WorkerPool() {
    tasks := make(chan int, 100)
    results := make(chan int, 100)

    // define specific quantities of worker goroutine
    for w := 0; w < 3; w++ {
        go func(id int, jobs <-chan int, results chan<- int) {
            for j := range jobs {
                fmt.Println("worker: ", id, ", processing job: ", j)
                time.Sleep(time.Second)
                results <- j * 2
            }
        }(w, tasks, results)

    }

    for j := 0; j < 9; j++ {
        tasks <- j
    }
    close(tasks)

    for a := 0; a < 9; a++ {
        // if not receive the msg in main routine, main won`t wait for the worker, and ends the proc
        <-results
    }
}
