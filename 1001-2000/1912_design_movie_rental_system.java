class MovieRentingSystem {
    // T.C. = O(nlogn)
    // S.C. = O(n)

    class MovieCopy {
        int shop, movie, price;
        MovieCopy(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }
    }

    // Data structure initialization
    private Map<Integer, TreeSet<MovieCopy>> movieToCopies;  // unrented movies
    private Map<String, MovieCopy> shopMovieMap;         // mapping for uniquely identifying moviecopy object    
    private TreeSet<MovieCopy> rented;   

    public MovieRentingSystem(int n, int[][] entries) {
        movieToCopies = new HashMap<>();
        shopMovieMap = new HashMap<>();
        // Onlogn
        rented = new TreeSet<>((a, b) -> {
            if (a.price != b.price) return a.price - b.price;
            if (a.shop != b.shop) return a.shop - b.shop;
            return a.movie - b.movie;
        });

        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            MovieCopy copy = new MovieCopy(shop, movie, price);

            shopMovieMap.put(shop + ":" + movie, copy);
            movieToCopies.putIfAbsent(movie, new TreeSet<>(
                (a, b) -> (a.price != b.price) ? a.price - b.price : a.shop - b.shop
            ));
            movieToCopies.get(movie).add(copy);
        }
    }

    /*
    1 - [obj2[1,1,4] <--> obj1[0,1,5]<--> obj3[2,1,5]], 
    2 - [obj4[0,2,6] <--> obj5[2,2,7]]
    */
    
    public List<Integer> search(int movie) {
        // O(1)
        List<Integer> result = new ArrayList<>();
        if (!movieToCopies.containsKey(movie)) return result;

        Iterator<MovieCopy> it = movieToCopies.get(movie).iterator();
        for (int count = 0; it.hasNext() && count < 5; count++) {
            result.add(it.next().shop); // [1,0,2]
        }
        return result;
    }
    
    public void rent(int shop, int movie) {
        // O(logn)
        MovieCopy copy = shopMovieMap.get(shop + ":" + movie);
        movieToCopies.get(movie).remove(copy); // unrented
        rented.add(copy); // add
    }
    
    public void drop(int shop, int movie) {
        // O(logn)
        MovieCopy copy = shopMovieMap.get(shop + ":" + movie);
        rented.remove(copy);
        movieToCopies.get(movie).add(copy);
    }
    
    public List<List<Integer>> report() {
        // O(1)
        // rented [sorted values of moviecopies objects]
        List<List<Integer>> result = new ArrayList<>();
        Iterator<MovieCopy> it = rented.iterator();
        for (int count = 0; it.hasNext() && count < 5; count++) {
            MovieCopy c = it.next();
            result.add(Arrays.asList(c.shop, c.movie));
        }
        return result;
    }
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();

MovieCopies

| Shop | Movie | Price |
| ---- | ----- | ----- |
| 0    | 1     | 5     |
| 0    | 2     | 6     |
| 1    | 1     | 4     |
| 2    | 1     | 5     |
| 2    | 2     | 7     |

Search: cheapest 5 shops with unrented copies of given movie

MovieCopy [movieid, shopid, price]

[Data Structure: Movie --> [list of all MovieCopies sorted on the basis of price i.e. a Treeset type of ds]]

------------movieCopies MAP---------------
1 - [obj2[1,1,4] <--> obj1[0,1,5]<--> obj3[2,1,5]], [1,0,2]
2 - [obj4[0,2,6] <--> obj5[2,2,7]]

Rent: Renting an unrented movie from a given shop
(1,1)
[Data Structure: collection of all rented moviecopies (set)
but we need to find MovieCopy object from previous map 
so another Data structure: [shop+movie]-->MovieCopy for unique object saving]
RentedSet - [obj2]
ShopToMovieCopy MAP
"0+1" --> obj1
"1+1" --> obj2
"2+1" --> obj3
"0+2" --> obj4
"2+2" --> obj5

Drop: unrenting, dropping off the movie
[removing from rented set, and adding to movieCopies map]

Report: cheapest five rented movies sorted --> price --> shopid --> movieid
[make use of rented set, but make it sorted --> so treeset]

[1--2--3--4--5--6--7] - rentedset
 */