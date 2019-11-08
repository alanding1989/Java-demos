package control_flow

import (
    "fmt"
)

type LoopObj struct{}

func NewLoopObj() *LoopObj {
    return &LoopObj{}
}

func (self *LoopObj) Loop() {
    i := 1
    for i <= 3 {
        fmt.Println(i)
        i = i + 1
    }

    for j := 7; j <= 9; j++ {
        fmt.Println(j)
    }

    for {
        fmt.Println("loop")
        break
    }

    for i := 0; i <= 5; i++ {
        if i%2 == 0 {
            continue
        }
        fmt.Println(i)
    }
}

// iter map-dict

func (self *LoopObj) Iter() {
    // iter slice-list
    self.Loop()
    sum := 0
    nums := []int{2, 3, 4}
    for _, n := range nums {
        sum += n
    }
    fmt.Println("sum is: ", sum)

    for i, num := range nums {
        if num == 3 {
            fmt.Println("\nindex of num is :", i)
        }
    }

    kvs := map[string]string{"a": "apple", "b": "banana"}
    for k, v := range kvs {
        fmt.Printf("%s -> %s\n", k, v)
    }

    // output ascii char
    for i, c := range "go" {
        fmt.Println(i, c)
    }
}
