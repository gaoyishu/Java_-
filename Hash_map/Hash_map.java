package cn.sxt.gaoyishu;

public class Hash_map<K,V> {

    Node[] table; //威桶数组 bucket array
    int size;

    public Hash_map(){
        table = new Node[16];
    }

    public K get(K key){

        int hash = myHash(key.hashCode(),table.length);
        Object value = null;

        if (table[hash]!=null){
            //取第一个节点
            Node temp = table[hash];

            while (temp!=null){
                if (temp.key.equals(key)){
                    value = (V)temp.value;
                    break;
                }else {
                    temp = temp.next;
                }
            }
        }
        return (K)value;
    }




    public void put(K key,V value){
        //定义了新的节点对象
        Node newNode = new Node();
        newNode.hash = myHash(key.hashCode(),table.length);
        newNode.key = key;
        newNode.value = value;
        newNode.next = null;

        Node temp = table[newNode.hash];

        Node iterLast = null;  //正在遍历的最后的元素

        boolean KeyRepeat = false;

        if (temp==null) {
            table[newNode.hash] = newNode;
            size++;
        }else {
            //如果此处不为空，就遍历链表，如果有重复就代替，没有就加在后面
            while (temp!=null){
                //判断key是否重复，则覆盖
                if (temp.key.equals(key)){
                    KeyRepeat = true;
                    temp.value = value; //只是覆盖value，其他值保持不变，因为hash和key相同

                    break;
                }else {
                    //key不重复，不需要覆盖，加在next后面
                    iterLast = temp;
                    temp = temp.next;
                }
            }
            if (!KeyRepeat){
            iterLast.next = newNode;
            size++;
            }

        }


    }

    public int myHash(int v,int length){
        System.out.println("hash in myHash:"+(v&(length-1)));
        return v&(length-1);  //位运算
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i=0;i<table.length;i++){
            Node temp = table[i];

            while (temp!=null){
                sb.append(temp.key+":"+temp.value+",");
                temp = temp.next;
            }
        }
        sb.setCharAt(sb.length()-1,'}');
        return sb.toString();
    }

    public static void main(String[] args){
        Hash_map<Integer,String> m = new Hash_map<>();
        m.put(10,"aa");
        System.out.println(m);

    }

}
