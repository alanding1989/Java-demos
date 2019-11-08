package basic_types

import (
    "fmt"
    "math"
)

const s string = "constant"

func Const() {
    fmt.Println(s)
    const n = 5000000
    
    const d = 3e20 / n
    fmt.Println(d)
    
    fmt.Println(int64(d))
    
    fmt.Println(math.Sin(d))
}

func Variable() {
    a := "initial"
    fmt.Println(a)
    
    b, c := 1, 2
    fmt.Println(b, c)
    
    d := true
    fmt.Println(d)
    
    var e int
    fmt.Println(e)
    
    f := "short"
    fmt.Println(f)
}
