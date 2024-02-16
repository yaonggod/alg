import java.util.*;

class Solution {
    static class Song implements Comparable<Song> {
        int index;
        int plays;
        
        public Song(int index, int plays) {
            this.index = index;
            this.plays = plays;
        }
        
        @Override 
        public int compareTo(Song song) {
            if (this.plays > song.plays) {
                return -1;
            } else if (this.plays < song.plays) {
                return 1;
            } else {
                if (this.index < song.index) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
    
    static class Genre implements Comparable<Genre> {
        String name;
        int totalPlay;
        
        public Genre(String name, int totalPlay) {
            this.name = name;
            this.totalPlay = totalPlay;
        }
        
        @Override
        public int compareTo(Genre genre) {
            if (this.totalPlay < genre.totalPlay) {
                return 1;
            }
            return -1;
        }
    }
    
    
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, Integer> genreMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            if (genreMap.containsKey(genres[i])) {
                genreMap.put(genres[i], genreMap.get(genres[i]) + plays[i]); 
            } else {
                genreMap.put(genres[i], plays[i]);
            }
        }
        
        PriorityQueue<Genre> genreQueue = new PriorityQueue<>();
        for (String g : genreMap.keySet()) {
            genreQueue.offer(new Genre(g, genreMap.get(g)));
        }
        
        Map<String, PriorityQueue<Song>> songList = new HashMap<>(); 
        for (int i = 0; i < plays.length; i++) {
            if (songList.containsKey(genres[i])) {
                songList.get(genres[i]).offer(new Song(i, plays[i]));
            } else {
                songList.put(genres[i], new PriorityQueue<>());
                songList.get(genres[i]).offer(new Song(i, plays[i]));
            }
        }
        
        List<Integer> answer = new ArrayList<>();
        
        while (!genreQueue.isEmpty()) {
            Genre g = genreQueue.poll();
            for (int i = 0; i < 2; i++) {
                if (!songList.get(g.name).isEmpty()) {
                    Song s = songList.get(g.name).poll();
                    answer.add(s.index);
                }
            }
        }
        
        
        int[] a = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            a[i] = answer.get(i);
        }
        return a;
    }
}