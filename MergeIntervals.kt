private fun main(){
    val intervals = arrayOf(
        intArrayOf(2,3),
        intArrayOf(5,5),
        intArrayOf(2,2),
        intArrayOf(3,4),
        intArrayOf(3,4))
    var output = merge(intervals)
    for (o in output){
        println(o.contentToString())
    }
}

fun merge(intervals: Array<IntArray>): Array<IntArray> {
    var output: Array<IntArray> = emptyArray()
    var i =0
    var j = 1
    while(i<intervals.size-1) {
        while (j < intervals.size) {
            if (checkOverlapped(intervals[i], intervals[j])) {
                val merged = mergeTwo(intervals[i], intervals[j])
                intervals[i] = merged
                break
            } else output += intervals[j]
            j++
        }
        i++
    }
    return output
}


private fun checkOverlapped(interval1: IntArray, interval2: IntArray): Boolean {
    return (interval1[0] in interval2[0]..interval2[1]
            || interval1[1] in interval2[0]..interval2[1]
            || interval2[0] in interval1[0]..interval1[1]
            || interval2[1] in interval1[0]..interval1[1])
}
private fun mergeTwo(interval1: IntArray, interval2: IntArray): IntArray {
    val output: IntArray = intArrayOf(0, 0)
    if (interval1[0] <= interval2[0]) {
        output[0] = interval1[0]
    } else output[0] = interval2[0]
    if (interval1[1] <= interval2[1])
        output[1] = interval2[1]
    else output[1] = interval1[1]
    return output
}
