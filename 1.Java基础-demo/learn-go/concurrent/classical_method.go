package concurrent

import (
    "fmt"
    "math/rand"
    "runtime"
    "sync"
    "sync/atomic"
    "time"
)

func AtomicExp() {
    runtime.GOMAXPROCS(4)
    var ops uint64 = 0
    for i := 0; i < 50; i++ {
        go func() {
            for {
                atomic.AddUint64(&ops, 1)

                runtime.Gosched()
            }
        }()
    }

    time.Sleep(time.Second)

    opsFinal := atomic.LoadUint64(&ops)
    fmt.Println("ops", opsFinal)
}

func MutexSync() {
    var (
        dict          = make(map[int]int)
        mutex         = &sync.Mutex{}
        counter int64 = 0
    )

    for r := 0; r < 100; r++ {
        go func() {
            total := 0
            for {
                key := rand.Intn(5)
                mutex.Lock()
                total += dict[key]
                mutex.Unlock()
                atomic.AddInt64(&counter, 1)

                runtime.Gosched()
            }
        }()
    }

    for w := 0; w < 10; w++ {
        go func() {
            for {
                key := rand.Intn(5)
                val := rand.Intn(100)
                mutex.Lock()
                dict[key] = val
                mutex.Unlock()
                atomic.AddInt64(&counter, 1)

                runtime.Gosched()
            }
        }()
    }

    time.Sleep(time.Second)

    opsFinal := atomic.LoadInt64(&counter)
    fmt.Println("counter", opsFinal)

    mutex.Lock()
    fmt.Println("dict", dict)
    mutex.Unlock()
}

//<editor-fold desc="@ impl above exp via channel">
type readOp struct {
    key  int
    resp chan int
}

type writeOp struct {
    key  int
    val  int
    resp chan bool
}

func ChanSync() {
    var (
        readCounter  uint64 = 0
        writeCounter uint64 = 0
    )

    writes := make(chan *writeOp)
    reads := make(chan *readOp)

    for w := 0; w < 10; w++ {
        go func() {
            write := &writeOp{
                key:  rand.Intn(5),
                val:  rand.Intn(100),
                resp: make(chan bool)}
            writes <- write
            <-write.resp
            atomic.AddUint64(&writeCounter, 1)
            time.Sleep(time.Millisecond)
        }()
    }

    for r := 0; r < 100; r++ {
        go func() {
            read := &readOp{
                key:  rand.Intn(5),
                resp: make(chan int)}
            reads <- read
            <-read.resp
            atomic.AddUint64(&readCounter, 1)
            time.Sleep(time.Millisecond)
        }()
    }

    go func() {
        dict := make(map[int]int)
        for {
            select {
            case write := <-writes:
                dict[write.key] = write.val
                write.resp <- true
            case read := <-reads:
                read.resp <- dict[read.key]
            }
        }
    }()

    time.Sleep(time.Second)

    readOpsFinal := atomic.LoadUint64(&readCounter)
    fmt.Println("readCounter: ", readOpsFinal)
    writeOpsFinal := atomic.LoadUint64(&writeCounter)
    fmt.Println("writeCounter: ", writeOpsFinal)
}
//</editor-fold>
