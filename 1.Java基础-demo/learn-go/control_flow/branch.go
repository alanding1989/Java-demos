package control_flow

import (
    "fmt"
    "time"
)

func Switch() {
    i := 2
    fmt.Println(i)
    switch i {
    case 1:
        fmt.Println("one")
    case 2:
        fmt.Println("two")
    case 3:
        fmt.Println("three")

    }

    switch time.Now().Weekday() {
    case time.Sunday, time.Saturday:
        fmt.Println("it is the weekend")
    default:
        fmt.Println("it`s afternoon")
    }

    t := time.Now()
    switch {
    case t.Hour() < 12:
        fmt.Println("it`s before noon")
    default:
        fmt.Println("it`s afternoon")
    }

    whatAmI := func(i interface{}) {
        switch t := i.(type) {
        case bool:
            fmt.Println("I`m a bool")
        case int:
            fmt.Println("I`m an int")
        default:
            fmt.Println("Don`t know type %T\n", t)
        }
    }

    whatAmI(true)
    whatAmI(1)
    whatAmI("hey")
}
