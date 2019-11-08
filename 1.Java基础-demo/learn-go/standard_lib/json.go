package standard_lib

import (
    "encoding/json"
    "fmt"
    "os"
)

type Response1 struct {
    Page   int
    Fruits []string
}

type Response2 struct {
    Page   int      `json:"page"`
    Fruits []string `json:"fruits"`
}

func JsonExp() {
    bolB, _ := json.Marshal(true)
    fmt.Println(string(bolB) + "\n")

    intB, _ := json.Marshal(1)
    fmt.Println(string(intB) + "\n")

    fltB, _ := json.Marshal(2.34)
    fmt.Println(string(fltB) + "\n")

    strB, _ := json.Marshal("gopher")
    fmt.Println(string(strB) + "\n")

    slcB, _ := json.Marshal([]string{"apple", "peach", "pear"})
    fmt.Println(string(slcB) + "\n")

    mapB, _ := json.Marshal(map[string]int{"apple": 5, "lettuce": 4})
    fmt.Println(string(mapB) + "\n")

    res1D := &Response1{
        Page:   1,
        Fruits: []string{"apple", "pear", "peach"}}
    res1B, _ := json.Marshal(res1D)
    fmt.Println(string(res1B) + "\n")

    res2D := Response2{
        Page:   1,
        Fruits: []string{"apple", "peach", "pear"}}
    res2B, _ := json.Marshal(res2D)
    fmt.Println(string(res2B) + "\n")

    // json to go
    byt := []byte(`{"num":6.13, "strs":["a", "b"]}`)
    // this will panic
    // byt := []byte(`{"num":6.13}, "strs":["a", "b"]}`)
    var dat map[string]interface{}
    if err := json.Unmarshal(byt, &dat); err != nil {
        panic(err)
    }
    fmt.Println(dat)
    fmt.Println()

    // cast assertion from interface to float64,
    if num, ok := dat["num"].(float64); ok {
        fmt.Println(num)
    }
    fmt.Println()

    if strs, ok := dat["strs"].([]interface{}); ok {
        if str1, ok := strs[0].(string); ok {
            fmt.Println(str1)
            fmt.Println()
        }
    }

    str := []byte(`{"page":1, "fruits": ["apple", "peach"]}`)
    res := &Response2{}
    if err := json.Unmarshal(str, &res); err != nil {
        panic(err)
    }
    fmt.Println(res)
    fmt.Println(res.Fruits[0])

    enc := json.NewEncoder(os.Stdout)
    d := map[string]int{"apple": 5, "lettuce": 7}
    if err := enc.Encode(d); err != nil {
        panic(err)
    }
}
