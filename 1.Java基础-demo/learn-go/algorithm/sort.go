package algorithm

import (
    "fmt"
    "sort"
)

func PrimitiveSort() {
    strs := []string{"c", "a", "b"}
    sort.Strings(strs)
    fmt.Println("Strings: ", strs)

    ints := []int{7, 2, 4}
    sort.Ints(ints)
    fmt.Println("Ints: ", ints)

    s := sort.IntsAreSorted(ints)
    fmt.Println("Sorted: ", s)
}

//<editor-fold desc="@ impl Sort.Interface">
type ByLength []string

func (self ByLength) Len() int {
    return len(self)
}

func (self ByLength) Less(i, j int) bool {
    return len(self[i]) < len(self[j])
}

func (self ByLength) Swap(i, j int) {
    self[i], self[j] = self[j], self[i]
}
//</editor-fold>

func CustomizeSort() {
    fruits := []string{"peach", "banana", "kiwi"}
    sort.Sort(ByLength(fruits))
    fmt.Println(fruits)
}

