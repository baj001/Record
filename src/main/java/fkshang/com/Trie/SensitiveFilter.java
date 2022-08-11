package fkshang.com.Trie;

import java.util.*;

/**
 * @author baj
 * @creat 2022-06-12 14:07
 */
public class SensitiveFilter {
    /**
     *
     * @Description: trie树（字典树）做敏感词过滤
     */
        /**
         * trie树的结点类（TrieTree的静态内部类）
         */
        private static class TrieNode {

            //当前结点的字母
            private char letter;

            //当前节点字母是否可以作为结束字母
            private boolean isEndLetter = false;

            //当前结点的下一结点集合
            private Set<TrieNode> next;

            /**
             * 空构造方法
             */
            public TrieNode() {

            }

            /**
             * 入参letter的构造方法
             * @param letter
             */
            public TrieNode(char letter) {
                this.letter = letter;

                //为每一个结点创建一个空的next集合
                this.next = new HashSet<>();
            }

            /**
             * 重写equals方法，结点字母相等，结点即相等
             * @param obj
             * @return
             */
            @Override
            public boolean equals(Object obj) {
                //如果不是同一类，则绝对不等
                if (!(obj instanceof TrieNode)) {

                    //返回不等
                    return false;
                }

                //字母相等，则结点相等，否则不等
                return this.letter == ((TrieNode)obj).letter;
            }

            /**
             * 重写了equals方法，就得跟着重写hashCode方法
             * @return
             */
            @Override
            public int hashCode() {
                //以letter的数值代表hashcode
                return this.letter;
            }

            /**
             * 查找某字母的下级字母结点
             * @param letter 查找的下一个字母
             * @return 下一个字母的结点
             */
            public TrieNode findNext(char letter) {
                //遍历查找
                for (TrieNode node : next) {

                    //如果找到了
                    if (node.letter == letter) {

                        //返回该结点
                        return node;
                    }
                }

                //没找到，返回null
                return null;
            }

        }


        /************** 接下来是TrieTree类的属性和方法 **************/

        //trie树的根结点，默认为'/'
        private TrieNode root = new TrieNode('/');

        //trie树的公共后缀（句子尾部的处理需要）
        private static final String PUBLIC_SUFFIX = "********";

        /**
         * 空构造方法
         */
        public SensitiveFilter() {

        }

        /**
         * 带初始敏感词数组的构造方法
         * @param words
         */
        public SensitiveFilter(String[] words) {
            //遍历敏感词数组
            for (int i = 0; i < words.length; i++) {

                //加入初始敏感词
                addWord(words[i]);
            }
        }

        /**
         * 加入需要过滤的敏感词（敏感词不能带*号）
         * @param word 待加入的敏感词
         */
        public void addWord(String word) {
            //验证敏感词里面是否带有*号（否则程序可能会出错）
            if (word.contains("*")) {

                //抛出非法参数异常
                throw new IllegalArgumentException("敏感词中不能含有*号");
            }

            //将敏感词转化为字符数组
            char[] charArray = word.toCharArray();

            //当前trie树结点为根结点
            TrieNode currentNode = root;

            //遍历敏感词每一个字母
            for (int i = 0; i < charArray.length; i++) {

                //在当前trie树结点的下一个字母结点集合里，寻找当前字母结点
                TrieNode nextNode = currentNode.findNext(charArray[i]);

                //如果当前字母存在
                if (nextNode != null) {

                    //找到的当前字母结点作为当前trie树结点
                    //接下来在当前字母结点的下一个字母集合里找下一个字母
                    currentNode = nextNode;

                    //如果当前字母不存在
                } else {

                    //为当前字母创造trie树结点
                    TrieNode trieNode = new TrieNode(charArray[i]);

                    //将当前字母创造的trie树结点，加入到当前结点的下一个结点集合里
                    currentNode.next.add(trieNode);

                    //以当前字母创造的trie树结点作为当前结点，继续找下一个字母
                    //有就继续看下一个字母，没有就创建，直到创建完整敏感词树枝
                    currentNode = trieNode;
                }
            }

            //将最后一个字母，设置为结束字母
            currentNode.isEndLetter = true;
        }

        /**
         * 过滤敏感词（用当前TrieTree类的敏感词库来过滤）
         * @param sentence 待过滤的原始句子
         * @return 敏感词字母被替换为*的句子
         */
        public String filtrate(String sentence) {
            //要处理的句子，统一加上公共后缀（句子尾部的处理需要）
            sentence = sentence + PUBLIC_SUFFIX;

            //将原始句子转化为字符数组
            char[] charArray = sentence.toCharArray();

            //过滤后的句子的字符列表
            List<Character> filtrated = new ArrayList<>();

            //遍历原始句子字符数组，从每一个字母开始向右同步走一遍trie树
            for (int i = 0; i < charArray.length; i++) {

                //字母和trie树的比对指标，就是同步比对到第几个字母了
                int index = i;

                //结束字母比对检查点，记录找到的从起始字母
                //到所有结束字母中最大长度的结束字母的下标
                int checkpoint = -1;

                //当前比对的trie树结点，用它的下一字母集合比对，初始化为root
                TrieNode currentNode = root;

                //持续比对
                while (true) {

                    //如果比对的字母已经超过了原始句子的字符数组边界
                    if (index > charArray.length - 1) {

                        //停止比对
                        break;
                    }

                    //当前trie树结点的下一字母集合的元素个数
                    int nextSize = currentNode.next.size();

                    //将当前字母在当前trie树结点的下一字母集合中寻找
                    //是否存在当前字母的trie树结点
                    currentNode = currentNode.findNext(charArray[index]);

                    //如果在trie树上找到了
                    if (currentNode != null) {

                        //如果是结束字母
                        if (currentNode.isEndLetter) {

                            //更新结束字母检查点
                            checkpoint = index;
                        }

                        //指标+1，也就是继续拿下一个字母
                        //和下一个trie树结点继续比对
                        index ++;

                        //如果trie树上没找到，且还没找到trie树的尽头，即叶结点
                    } else if (nextSize > 0) {

                        //如果检查点不为初始值-1，也就是存在前缀敏感词
                        if (checkpoint != -1) {

                            //首先我们得到前缀敏感词的长度
                            int prefixWordLength = checkpoint - i + 1;

                            //遍历敏感词长度的次数
                            for (int j = 0; j < prefixWordLength; j++) {

                                //加入等长的*符号
                                filtrated.add('*');
                            }

                            //将原始句子的遍历指标置为前缀
                            //敏感词最后一个字母的位置
                            i = checkpoint;

                            //如果检查点为i，也就是不存在前缀敏感词
                        } else {

                            //说明以原始句子字母开头的所有词中不存在敏感词
                            //将该字母原样装入到过滤后的句子的字符列表
                            filtrated.add(charArray[i]);
                        }

                        //结束比对
                        break;

                        //如果trie树上没找到，且找到了trie树的尽头，即叶结点
                        //那就是找到了以原始句子字母开头的敏感词
                        //我们的处理方式是用 "*" 代替敏感词的字母
                    } else {

                        //首先，我们得到敏感词的长度
                        int wordLength = index - i;

                        //遍历敏感词长度的次数
                        for (int j = 0; j < wordLength; j++) {

                            //加入等长的*符号
                            filtrated.add('*');
                        }

                        //将原始句子的遍历指标置为
                        //敏感词最后一个字母的位置
                        i = index - 1;

                        //结束比对
                        break;
                    }
                }
            }
            //至此，过滤后的句子的字符列表已创建完毕

            //创建StringBuffer对象
            StringBuffer sbuf = new StringBuffer();

            //遍历过滤后的句子字符列表
            for (Character character : filtrated) {

                //将过滤后句子的字符拼接
                sbuf.append(character);
            }

            //返回原始句子过滤后的句子（截取尾部公共后缀前的句子）
            return sbuf.substring(0, sbuf.length() - PUBLIC_SUFFIX.length());
        }


        /**
         * 测试trie树的敏感词过滤功能
         * @param args
         */
        public static void main(String[] args) {
            //敏感词字符串数组
            String[] words = {"hello", "hell", "he", "hero", "see", "sun"};

            //额外加入的敏感词
            String extraWord = "right";

            //创建TrieTree实例，初始化加入words的六个敏感词
            SensitiveFilter trieTree = new SensitiveFilter(words);

            //继续加入第七个敏感词
            trieTree.addWord(extraWord);

            //过滤前的句子
            String sentence = "hellofsxhelllgfeheldxghergasgherohgseevsunfright";

            //打印过滤前的句子
            System.out.println("过滤前：" + sentence);

            //调用TrieTree示例的方法，将句子进行敏感词过滤
            String filtrated = trieTree.filtrate(sentence);

            //打印过滤后的句子，用作比对
            System.out.println("过滤后：" + filtrated);
        }

}
