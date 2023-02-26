import sys
input = sys.stdin.readline

tree = [[-1, -1] for _ in range(26)]

n = int(input())
for _ in range(n):
    p, c1, c2 = map(str, input().split())
    if ord(c1) - 65 >= 0 and ord(c1) - 65 < 26:
        tree[ord(p) - 65][0] = ord(c1) - 65
    if ord(c2) - 65 >= 0 and ord(c2) - 65 < 26:
        tree[ord(p) - 65][1] = ord(c2) - 65

pretree = ""
intree = ""
posttree = ""

def preorder(i):
    global pretree
    pretree += chr(i + 65)
    if tree[i][0] != -1:
        preorder(tree[i][0])
    if tree[i][1] != -1:
        preorder(tree[i][1])

def inorder(i):
    global intree
    if tree[i][0] != -1:
        inorder(tree[i][0])
    intree += chr(i + 65)
    if tree[i][1] != -1:
        inorder(tree[i][1])

def postorder(i):
    global posttree
    if tree[i][0] != -1:
        postorder(tree[i][0]) 
    if tree[i][1] != -1:
        postorder(tree[i][1])
    posttree += chr(i + 65)
    
preorder(0)
inorder(0)
postorder(0)

print(pretree)
print(intree)
print(posttree)