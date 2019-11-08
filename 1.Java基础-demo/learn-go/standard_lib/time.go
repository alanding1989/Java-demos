package standard_lib

import (
    "fmt"
    "time"
)

func TimeExp() {
    now := time.Now()
    fmt.Println("Now the time is: ", now)
    fmt.Println()

    time.Sleep(time.Second * 2)
    // time duration
    now1 := time.Now().Sub(now)
    fmt.Println(now1.Seconds())
    fmt.Println(now1.Round(time.Second))
    fmt.Println()

    // query
    then := time.Date(2019, 11, 17, 20, 34, 58, 4223, time.Local)
    fmt.Println(then)
    fmt.Println()

    fmt.Println(then.Year())
    fmt.Println(then.Month())
    fmt.Println(then.Weekday())
    fmt.Println()

    fmt.Println(then.Location())
    fmt.Println()

    fmt.Println(then.After(now))
    fmt.Println(then.Equal(now))

    fmt.Println(then.Add(time.Second * 100))
}

func TimeStamp() {
    now := time.Now()
    secs := now.Unix()
    nanos := now.UnixNano()
    millis := nanos / 1000000

    fmt.Println(now)
    fmt.Println(secs)
    fmt.Println(nanos)
    fmt.Println(millis)
}

func TimeFmt() {
    now := time.Now()
    fmt.Println(now.Format(time.RFC822))
}