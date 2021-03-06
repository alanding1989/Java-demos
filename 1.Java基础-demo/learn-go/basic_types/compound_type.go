package basic_types

import (
    "fmt"
)

func Arrays() {
    // auto initiate
    var a [5]int
    fmt.Println("emp", a)

    a[4] = 100
    fmt.Println("set:", a)
    fmt.Println("get:", a[4])

    fmt.Println("len:", len(a))

    b := [5]int{1, 2, 3, 4, 5}
    fmt.Println("dcl:", b)

    // declare first
    var twoD [2][3]int
    for i := 0; i < 2; i++ {
        for j := 0; j < 3; j++ {
            twoD[i][j] = i + j
        }
    }
    fmt.Println("2d", twoD)
}

func Slice() {
    // empty slice
    s := make([]string, 3)
    fmt.Println("emp", s)

    s[0] = "a"
    s[1] = "b"
    s[2] = "c"
    fmt.Println("set:", s)
    fmt.Println("get:", s[2])

    fmt.Println("len:", len(s))

    s = append(s, "d")
    s = append(s, "e", "f")
    fmt.Println("append:", s)

    c := make([]string, len(s))
    copy(c, s)
    fmt.Println("cpy s", c)

    l := s[2:5]
    fmt.Println("slice cut", l)

    t := []string{"g", "h", "i"}
    fmt.Println("define: t", t)

    twoD := make([][]int, 3)
    fmt.Println("dcl:", twoD)
    for i := 0; i < 3; i++ {
        innerLen := i + 1
        twoD[i] = make([]int, 10)
        for j := 0; j < innerLen; j++ {
            twoD[i][j] = i + j
        }
    }
    fmt.Println("2d", twoD[2][2])
}

func Dict() {
    m := make(map[string]int)

    m["k1"] = 7
    fmt.Println("map", m)

    v1 := m["k1"]
    fmt.Println("v1:", v1)

    delete(m, "k2")

    // if key doesn`t exits, return default value and a false
    if _, prs := m["k2"]; !prs {
        fmt.Println("key 'k2' doesn`t exist ")
    }

    n := map[string]int{"foo": 1, "bar": 2}
    fmt.Print("map n is: ", n)
}
