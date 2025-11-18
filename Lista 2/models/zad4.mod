# Zadanie 4 – najtañsza œcie¿ka z ograniczeniem na czas

set N;                         # wierzcho³ki
set A within {N, N};           # ³uki skierowane

param c{A}   >= 0;             # koszt przejazdu
param t{A}   >= 0;             # czas przejazdu
param b{N}   default 0;        # +1 Ÿród³o, -1 ujœcie, 0 pozosta³e
param Tlim   >= 0;             # limit czasu przejazdu

var x{A} binary;               # czy u¿ywamy ³uku (i,j) w œcie¿ce

# Funkcja celu – minimalizacja sumy kosztów
minimize TotalCost:
    sum{(i,j) in A} c[i,j] * x[i,j];

# Równania przep³ywu (zachowanie ci¹g³oœci œcie¿ki)
s.t. FlowBalance{v in N}:
    sum{(v,j) in A} x[v,j]
  - sum{(i,v) in A} x[i,v] = b[v];

# Ograniczenie na ³¹czny czas przejazdu
s.t. TimeLimit:
    sum{(i,j) in A} t[i,j] * x[i,j] <= Tlim;

solve;

printf "Minimalny koszt: %g\n", TotalCost;
printf "Calkowity czas przejazdu: %g\n",
       sum{(i,j) in A} t[i,j] * x[i,j];

printf "\nKrawedzie nalezace do optymalnej sciezki:\n";
printf {(i,j) in A: x[i,j] > 0.5} "%d -> %d\n", i, j;

end;
