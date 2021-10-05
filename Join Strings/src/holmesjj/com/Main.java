package holmesjj.com;

import java.io.DataInputStream;
import java.io.IOException;

public class Main {

    public static class Reader {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private final byte[] buf;
        private int bufferPointer, bytesRead;
        private Position[] positions;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
            buf = new byte[1000000];
        }

        public byte[] getBuf() {
            return buf;
        }

        public Position[] getPositions() {
            return positions;
        }

        public void readLines(int n) throws IOException {
            positions = new Position[n];
            int cnt = 0, c;
            int line = 0;
            int start = 0;
            int end = 0;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    end = cnt - 1;
                    positions[line] = new Position(start, end);
                    line++;
                    start = cnt;
                    if (line == n) {
                        break;
                    }
                    continue;
                }
                buf[cnt++] = (byte)c;
            }
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }
    }

    public static class Position {

        private final int start;
        private final int end;

        public Position(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

    public static class Node {

        private final Position position;
        private Node next;
        private Node end;

        public Node(Position position) {
            this.position = position;
            this.end = this;
            this.next = null;
        }

        public Node(Position position, Node end) {
            this.position = position;
            this.end = end;
            this.next = null;
        }

        public Position getPosition() {
            return position;
        }

        public void addNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public void setEnd(Node end) {
            this.end = end;
        }

        public Node getEnd() {
            return end;
        }
    }

    public static void main(String[] args) throws IOException {

        Reader reader = new Reader();
        int N = reader.nextInt();
        reader.readLines(N);
        Position[] positions = reader.getPositions();
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            Position position = positions[i - 1];
            nodes[i] = new Node(position);
        }
        if (N == 1) {
            Position position = nodes[1].getPosition();
            System.out.println(new String(reader.getBuf(), position.getStart(), position.getEnd() - position.getStart() + 1));
        } else {
            int index = 0;
            for (int i = 1; i < N; i++) {
                int a = reader.nextInt();
                int b = reader.nextInt();
                Node node = nodes[a];
                node.getEnd().addNext(nodes[b]);
                node.setEnd(nodes[b].getEnd());
                index = a;
            }
            Node current = nodes[index];
            while (current != null) {
                Position position = current.getPosition();
                System.out.print(new String(reader.getBuf(), position.getStart(), position.getEnd() - position.getStart() + 1));
                current = current.getNext();
            }
            reader.close();
        }
    }
}