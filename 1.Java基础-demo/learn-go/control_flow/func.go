package control_flow

import "fmt"

func Vals() (a int, b int) {
    fmt.Printf("as is %s, b is %s ", a, b)
    return 3, 7
}

func Sum(nums ...int) {
    fmt.Print(nums, " ")

    total := 0
    for _, num := range nums {
        total += num
    }
    fmt.Println(total)
}

func IntSeq() func() int {
    i := 0
    return func() int {
        i++
        return i
    }
}
