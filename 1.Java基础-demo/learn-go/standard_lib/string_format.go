package standard_lib

import (
    "fmt"
)

type point struct {
    x, y int
}

func StrFmt() {
    p := point{1, 2}
    fmt.Printf("%v\n", p)
    fmt.Printf("%+v\n", p)
    fmt.Printf("%#v\n", p) // type and value presentation

    fmt.Printf("%T\n", p)
    fmt.Printf("%t\n", true)
    fmt.Printf("%p\n", &p)   // the add of pointer
    fmt.Printf("%d\n", 123)
    fmt.Printf("%b\n", 14)
    fmt.Printf("%c\n", 33)
    fmt.Printf("%x\n", 456)
    fmt.Printf("%f\n", 78.9)
    fmt.Printf("%e\n", 1230000.000)
    fmt.Printf("%s\n", "\"string\"")
}
