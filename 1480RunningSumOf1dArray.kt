fun main(){
var nums = intArrayOf(1,2,3,4)
    var output = runningSum(nums)
println(output.contentToString())
}

fun runningSum(nums: IntArray): IntArray {
    var output = IntArray(nums.size)
    var i = 0
    var k: Int
    while (i < nums.size) {
        k = 0
        while (k <= i) {
            output[i] += nums[k]
            k++
        }
        i++
    }
    return output
}