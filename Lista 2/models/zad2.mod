# Zadanie 2 – plan tygodniowej produkcji

set P;           # produkty 
set M;           # maszyny 

param price{P}      >= 0;   # cena sprzeda¿y [$/kg]
param demand{P}     >= 0;   # maksymalny popyt [kg/tydzieñ]
param mat_cost{P}   >= 0;   # koszt materia³u [$/kg]
param mach_cost{M}  >= 0;   # koszt pracy maszyny [$/godzina]
param time{P,M}     >= 0;   # czas obróbki [minuty/kg]
param H             >= 0;   # dostêpnoœæ maszyny [godzin/tydzieñ]

var x{P} >= 0;               # produkcja produktu p [kg/tydzieñ]

# Funkcja celu: maksymalizacja zysku
maximize Profit:
    # przychód
    sum{p in P} price[p] * x[p]
  - # koszt materia³u
    sum{p in P} mat_cost[p] * x[p]
  - # koszt pracy maszyn
    sum{m in M} mach_cost[m] / 60 *
        sum{p in P} time[p,m] * x[p];

# Ograniczenia pojemnoœci maszyn (czas pracy)
s.t. Capacity{m in M}:
    sum{p in P} time[p,m] * x[p] <= H * 60;

# Ograniczenia popytu na produkty
s.t. MaxDemand{p in P}:
    x[p] <= demand[p];

solve;

printf "Maksymalny zysk: %g\n", Profit;

printf "\nOptymalny plan produkcji (x[p] > 0):\n";
printf {p in P: x[p] > 1e-6} "Produkt %s : %g kg\n", p, x[p];

end;
