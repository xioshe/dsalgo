package org.example.base.algo.sort

class QuickSortKt {

    fun sort(arr: IntArray): IntArray {
        quickSort(arr, 0, arr.size - 1)
        return arr
    }

    private fun quickSort(arr: IntArray, left: Int, right: Int) {
        // 子数组长度为 1 时不再排序
        if (left >= right) return
        val pivot = partition(arr, left, right)
        // 可能出现 pivot - 1 < left 的情况，被 left>= right 条件过滤了
        quickSort(arr, left, pivot - 1)
        quickSort(arr, pivot + 1, right)
    }

    /**
     * 核心是哨兵划分 [left, right]
     */
    private fun partition(arr: IntArray, left: Int, right: Int): Int {
        val median = median(arr, left, (left + right) / 2, right)
        swap(arr, left, median)
        val pivot = arr[left]
        var i = left
        var j = right
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--
            }
            while (i < j && arr[i] <= pivot) {
                i++
            }
            swap(arr, i, j)
        }
        swap(arr, i, left)
        return i
    }

    /**
     * 返回 left、right、mid 三者中位数
     */
    private fun median(arr: IntArray, left: Int, mid: Int, right: Int): Int {
        return if ((arr[left] < arr[mid]) xor (arr[left] < arr[right])) {
            left
        } else if ((arr[mid] < arr[left]) xor (arr[mid] < arr[right])) {
            mid
        } else {
            right
        }
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        if (i == j) return
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
}

fun main() {
    val arr = intArrayOf(3, 2, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
    QuickSortKt().sort(arr)
    println(arr.contentToString())
}