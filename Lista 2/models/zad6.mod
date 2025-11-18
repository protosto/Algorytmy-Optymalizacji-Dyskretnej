set R;                # wiersze
set C;                # kolumny

param k integer >= 1;                 # zasieg kamery
param isCont{R,C} >= 0, <= 1;         # 1 jesli w polu (r,c) stoi kontener

var y{R,C} binary;                    # 1 jesli w (r,c) ustawiamy kamere

# Kamera nie moze stac na kontenerze
s.t. NoCameraOnContainer{r in R, c in C}:
    y[r,c] <= 1 - isCont[r,c];

# Kazdy kontener musi byc obserwowany (w pionie lub poziomie, do k pol)
s.t. Cover{p in R, q in C: isCont[p,q] = 1}:
    sum{r in R, c in C:
          ( (r = p and c >= q-k and c <= q+k) or
            (c = q and r >= p-k and r <= p+k) ) } y[r,c] >= 1;

# Minimalizujemy liczbe kamer
minimize NumCams:
    sum{r in R, c in C} y[r,c];

solve;

printf "Minimalna liczba kamer: %d\n", NumCams;

printf "\nKamery ustawiamy w polach (r,c):\n";
printf {r in R, c in C: y[r,c] > 0.5} "(%d,%d)\n", r, c;

end;
