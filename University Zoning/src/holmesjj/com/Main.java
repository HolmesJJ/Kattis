package holmesjj.com;

import java.util.*;
import java.io.*;

class Stu implements Comparable<Stu>{
    int r, c, d, f;

    @Override
    public int compareTo(Stu o){
        return this.d-o.d;
    }
}

class Pos implements Comparable<Pos>{
    int r, c;
    @Override
    public int compareTo(Pos o){
        if(o.r==this.r) return this.c-o.c;
        return this.r-o.r;
    }
}

public class Main {
    static StreamTokenizer re;
    public static int nextInt() throws IOException{
        re.nextToken();
        return (int) re.nval;
    }
    public static void main(String[] args) throws IOException{
        re = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int R = nextInt(), C = nextInt();
        int F = nextInt(), S = nextInt();
        int G = nextInt(); // at least
        ArrayList<Pos> pos[] = new ArrayList[F+1];
        ArrayList<Stu> stu[] = new ArrayList[F+1];
        for(int i = 1; i<=F; i++){
            int k = nextInt();
            pos[i] = new ArrayList<Pos>();
            stu[i] = new ArrayList<Stu>();
            for(int j = 0; j<k; j++){
                Pos p = new Pos();
                p.r = nextInt(); p.c = nextInt();
                pos[i].add(p);
            }
            Collections.sort(pos[i]);
        }


        for(int i = 1; i<=S; i++){
            Stu s = new Stu();
            s.r = nextInt(); s.c = nextInt();
            s.d = nextInt(); s.f = nextInt();
            stu[s.f].add(s);
        }

        Integer T[] = new Integer[F+1];

        for(int i = 1; i<=F; i++){
            Collections.sort(stu[i]);
            T[i] = nextInt();
        }

        for(int i = 1; i<=F; i++){
            for(int j = 0; j<stu[i].size(); j++){
                Stu s = stu[i].get(j);
                s.d = j;
                stu[i].set(j, s);
            }
        }

        ArrayList<Long> dis[] = new ArrayList[F+1];

        for(int i = 1; i<=F; i++){
            dis[i] = new ArrayList<Long>();
            for(int j = 0; j<stu[i].size(); j++){
                Stu s = stu[i].get(j);
                long d = (long)Math.abs(s.r-pos[i].get(s.d).r)+(long)Math.abs(s.c-pos[i].get(s.d).c);
                dis[i].add(d);
            }
            Collections.sort(dis[i]);
        }

        Long tmp[] = new Long[F+1];
        tmp[0] = 0l;
        for(int i = 1; i<=F; i++){
            tmp[i] = 0l;
            for(int j = 0; j<T[i]; j++){
                tmp[i]+=dis[i].get(j);
            }
        }
        Arrays.sort(tmp);
        Long ans = 0l;
        for(int i = 1; i<=G; i++){
            ans+=tmp[i];
        }
        System.out.println(ans);
    }
}