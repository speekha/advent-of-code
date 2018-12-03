package com.adventofcode.dec2018.day3

data class Claim(val id: String, val left: Int, val top: Int, val width: Int, val height: Int) {

    operator fun contains(point: Pair<Int, Int>) = point.first in left until left + width && point.second in top until top + height

    operator fun contains(claim: Claim) = left to top in claim || left + width - 1 to top in claim || left to top + height - 1 in claim || left + width - 1 to top + height - 1 in claim

    infix fun intersects(claim: Claim) =
            left to top in claim
                    || left + width - 1 to top in claim
                    || left to top + height - 1 in claim
                    || left + width - 1 to top + height - 1 in claim
                    || claim.left to claim.top in this
                    || claim.left + claim.width - 1 to claim.top in this
                    || claim.left to claim.top + claim.height - 1 in this
                    || claim.left + claim.width - 1 to claim.top + claim.height - 1 in this

}
