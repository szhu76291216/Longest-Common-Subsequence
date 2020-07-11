# Longest-Common-Subsequence

Suppose we have a string `x` with length `n1` and a string `y` with length `n2` and the string index starts at 1. We define $C(i, j)$ as the longest subsequence of substrings `x[1:i]` and `y[1:j]`, i.e.
$$
C(i, j) = | LCS(x[1:i], y[1:j])|
$$
where  `x[m:n]` is the substring including the characters from index `m` to index `n`.

Then if `x[i]` and `y[j]` are the same, we can calculate `C(i, j)` as

$$
C(i, j) = C(i-1, j-1)
$$

And if they are not the same, we either discard `x[i]` or discard `y[j]`

$$
C(i, j) = \max \{C(i-1, j), C(i, j-1)\}
$$

The pseudo-code for dynamic programming is

```pascal
m = X.length
n = Y.length
let from[1..m, 1..n] and dp[0..m, 0..n] be new tables
for i = 1 to m
	dp[i, 0] = 0
for j = 0 to n
	dp[0, j] = 0
for i = 1 to m
	for j = 1 to n
		if X[i] == Y[j]
			dp[i, j] = dp[i-1, j-1] + 1
			from[i, j] = diag
		else if dp[i-1, j] >= dp[i, j-1]
			dp[i, j] = dp[i-1, j]
			from[i, j] = up
		else
			dp[i, j] = dp[i. j-1]
			from[i, j] = left
return dp, from
	
```

Then, we back trace from `from[m, n]` to get the LCS

- If `from[i, j] == diag`, then we add `X[i]` to the LCS string, do `i--, j--` and continue
- if `from[i, j] == left`, then we perform `j--` and continue
- if `from[i, j] == up`, then we perform `i--` and continue
- if `i == 0` or `j == 0`, then back-tracing stops

Since dynamic programming needs to fill a table of size $n1n2$,  and back tracing takes at most $O(n1+n2)$ time, we have an algorithm that runs in $O(n1n2)$ time.
