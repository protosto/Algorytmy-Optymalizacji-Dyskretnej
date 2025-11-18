set P;            # dzielnice
set S;            # zmiany

param minPS{P,S} >= 0;    # minimalna liczba radiowozów p,s
param maxPS{P,S} >= 0;    # maksymalna liczba radiowozów p,s

param minShift{S} >= 0;   # minimalna liczba radiowozów na zmianie s
param minDist{P}  >= 0;   # minimalna liczba radiowozów w dzielnicy p

var x{P,S} integer >= 0;  # liczba radiowozów w dzielnicy p na zmianie s

# ograniczenia min/max w ka¿dej komórce
s.t. CellMin{p in P, s in S}:
    x[p,s] >= minPS[p,s];

s.t. CellMax{p in P, s in S}:
    x[p,s] <= maxPS[p,s];

# minimalna obsada ka¿dej zmiany
s.t. ShiftMin{s in S}:
    sum{p in P} x[p,s] >= minShift[s];

# minimalna obsada ka¿dej dzielnicy (po wszystkich zmianach)
s.t. DistMin{p in P}:
    sum{s in S} x[p,s] >= minDist[p];

# funkcja celu: minimalizujemy ³¹czn¹ liczbê radiowozów
minimize TotalCars:
    sum{p in P, s in S} x[p,s];

solve;

printf "Minimalna laczna liczba radiowozow: %d\n", TotalCars;

printf "\nOptymalny przydzial (dzielnica, zmiana -> liczba radiowozow):\n";
printf {p in P, s in S} "%s, zmiana %s : %d\n", p, s, x[p,s];

end;
