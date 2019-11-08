package algorithm

import (
    "fmt"
    "strings"
)

func StringExp() {
    s := []string{"peach", "apple", "pear", "banana"}
    fmt.Println(index(s, "pear"))
    fmt.Println(include(s, "peach"))

    fmt.Println(any(s, func(v string) bool {
        return strings.HasPrefix(v, "p")
    }))
    fmt.Println(all(s, func(v string) bool {
        return strings.Contains(v, "a")
    }))
    fmt.Println(filter(s, func(v string) bool {
        return strings.HasPrefix(v, "p")
    }))

    fmt.Println(Map(s, func(v string) string {
        if strings.HasPrefix(v, "p") {
            return strings.ToUpper(v)
        }
        return v
    }))
}

func index(s []string, t string) int {
    for i, v := range s {
        if v == t {
            return i
        }
    }
    return -1
}

func include(s []string, t string) bool {
    return index(s, t) >= 0
    // for _, v := range s {
    //     if v == t {
    //         return true
    //     }
    // }
    // return false
}

func any(s []string, f func(string) bool) bool {
    for _, val := range s {
        if f(val) {
            return true
        }
    }
    return false
}

func all(s []string, f func(string) bool) bool {
    for _, v := range s {
        if !f(v) {
            return false
        }
    }
    return true
}

func filter(s []string, f func(string) bool) []string {
    ns := make([]string, 0)
    for _, v := range s {
        if f(v) {
            ns = append(ns, v)
        }
    }
    return ns
}

func Map(s []string, f func(string) string) []string {
    ns := make([]string, 0)
    for _, v := range s {
        ns = append(ns, f(v))
    }
    return ns
}
