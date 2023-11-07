package leetcode.graphs.unionfind

import java.util.*


/**
 * https://leetcode.com/problems/accounts-merge/description/
 */
class SolutionAccountsMerge {

    fun accountsMerge2(accounts: List<List<String>>): List<List<String>> {
        // create uf, size of accounts (max size)
        val uf = UnionFind(accounts.size)

        // hashmap linking emails to account ids
        val emailToAccountId = hashMapOf<String, Int>()
        for (i in accounts.indices) {
            for (j in 1 until accounts[i].size) {
                val email = accounts[i][j]
                if (emailToAccountId.containsKey(email)) {
                    // repeated email, account merge
                    val previousAccount = emailToAccountId[email]!!
                    uf.union(previousAccount, i)
                } else {
                    // new email
                    emailToAccountId[email] = i
                }
            }
        }

        // hashmap linking account id to list of emails
        val mergedAccounts = hashMapOf<Int, TreeSet<String>>()
        for (i in accounts.indices) {
            val parentId = uf.find(i)

            if (mergedAccounts[parentId] == null) mergedAccounts[parentId] = TreeSet()
            mergedAccounts[parentId]!!.addAll(accounts[i].subList(1, accounts[i].size))
        }

        // hashmap of
        val result = mutableListOf<List<String>>()
        mergedAccounts.forEach {
            val accountName = accounts[it.key][0]
            val mergedAccount = mutableListOf<String>()
            mergedAccount.add(accountName)
            mergedAccount.addAll(it.value)
            result.add(mergedAccount)
        }

        return result
    }


    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        val uf = UnionFind(accounts.size)

        // Initialize UnionFind with all the accounts (even repeated ones)
        val emailToNameId: HashMap<String, Int> = HashMap()

        for (i in accounts.indices) {
            for (j in 1 until accounts[i].size) {
                val email = accounts[i][j]
                if (emailToNameId.containsKey(email)) {
                    // we found a repeated email in two different accounts
                    val oldMapNameId = emailToNameId[email]!!
                    // merge accounts
                    uf.union(oldMapNameId, i)
                } else {
                    // populate hashmap with email to name id
                    emailToNameId[email] = i
                }
            }
        }

        // add all emails of each account to a new hashmap merging repeated accounts
        val idToEmailMap: HashMap<Int, TreeSet<String>> = HashMap()
        for (i in accounts.indices) {
            val rootParent: Int = uf.find(i) //p[0] -> 1
            val email = accounts[i]
            idToEmailMap.putIfAbsent(rootParent, TreeSet()) // emails of 0 and 1 will come under parent 1
            idToEmailMap[rootParent]!!.addAll(email.subList(1, email.size))
        }

        // constructing the answer
        val res: MutableList<List<String>> = LinkedList()
        for (id in idToEmailMap.keys) {
            val name = accounts[id][0]
            val emails: LinkedList<String> = LinkedList(idToEmailMap[id])
            emails.add(0, name)
            res.add(emails)
        }
        return res
    }

}

fun main() {
    SolutionAccountsMerge().apply {
        println("case 1")
        println(
            accountsMerge2(
                listOf(
                    listOf("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                    listOf("John", "johnsmith@mail.com", "john00@mail.com"),
                    listOf("Mary", "mary@mail.com"),
                    listOf("John", "johnnybravo@mail.com"),
                )
            )
        )
        println(
            accountsMerge(
                listOf(
                    listOf("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                    listOf("John", "johnsmith@mail.com", "john00@mail.com"),
                    listOf("Mary", "mary@mail.com"),
                    listOf("John", "johnnybravo@mail.com"),
                )
            )
        )
    }
}