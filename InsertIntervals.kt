// 57. Insert Interval
fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    var output: Array<IntArray> = emptyArray()
    when {
        intervals.isEmpty() -> {
            output += newInterval
        }
        newInterval.isEmpty() -> {
            output = intervals
        }
        !checkAnyOverlaps(intervals,newInterval) -> {
            output = outputNoOverlaps(intervals,newInterval)
        }
        else -> {
            var temp1 = Int.MAX_VALUE
            var temp2 = Int.MIN_VALUE
            val mergedInt: IntArray = intArrayOf(0, 0)
            for (interval in intervals) {
                if (isOverlapped(interval, newInterval) || isOverlapped(newInterval, interval)) {
                    val out1 = outInterval(interval, newInterval)[0]
                    val out2 = outInterval(interval, newInterval)[1]
                    if (temp1 > out1) {
                        temp1 = out1
                    }
                    if (temp2 < out2) {
                        temp2 = out2
                    }
                    mergedInt[0] = temp1
                    mergedInt[1] = temp2
                    if (mergedInt !in output) output += mergedInt
                } else {
                    output += interval
                }
            }
        }
    }
    return output
}

fun checkAnyOverlaps(intervals: Array<IntArray>, newInterval: IntArray): Boolean{
    var flag = false
    for(interval in intervals){
        flag = isOverlapped(interval,newInterval)
        if(flag) break
    }
    return flag
}

fun outputNoOverlaps(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    var output: Array<IntArray> = emptyArray()
    for (interval in intervals) {
        if (newInterval[1] < interval[0]) {
            if (newInterval !in output)
                output += newInterval
        }
        output += interval
    }
    if (newInterval[0] > intervals.last()[1]) {
        if (newInterval !in output)
            output += newInterval
    }
    return output
}

fun isOverlapped(interval: IntArray, newInterval: IntArray): Boolean {
    var isOverlapped = false
    for (newIntervalVal in newInterval.first()..newInterval.last())
        if (newIntervalVal >= interval[0] && newIntervalVal<=interval[1]) {
            isOverlapped = true
        }
    return isOverlapped
}
fun outInterval(interval1: IntArray, interval2: IntArray): IntArray {
    val outIntArray: IntArray = intArrayOf(0, 0)
    if (interval1[0] <= interval2[0]) outIntArray[0] = interval1[0]
    else outIntArray[0] = interval2[0]
    if (interval1[1] <= interval2[1]) outIntArray[1] = interval2[1]
    else outIntArray[1] = interval1[1]
    return outIntArray
}

fun main() {
    val intervals =
        arrayOf(intArrayOf(1, 3), intArrayOf(6,9))
    val newInterval = intArrayOf(2,5)
    val output = insert(intervals,newInterval)
    for (o in output) print(o.contentToString())
}