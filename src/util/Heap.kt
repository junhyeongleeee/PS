package util

import java.util.Collections.swap


fun maxHeapify(arr: IntArray, i: Int) {
    var largest = i
    var left = 2*i          // left child
    var right = 2*i + 1     // right child

    // 현재 요소 i 와 자식 노드의 값을 비교
    if(left <= arr.size && arr[left] > arr[i]) {
        largest = left
    }
    if (right <= arr.size && arr[right] > arr[largest]) {
        largest = right
    }

    // 자식 노드의 값이 더 크다면 교환하고 교환된 자식 노드부터 heapify 진행
    if (largest != i) {
        swap(arr.toList(), arr[i], arr[largest])
        maxHeapify(arr, largest)
    }
}