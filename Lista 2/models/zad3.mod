# Zadanie 3 – planowanie produkcji z magazynem

param K;              # liczba okresów
set T := 1..K;        # okresy (j = 1..K)

# Koszty i parametry dla ka¿dego okresu
param c{T}   >= 0;    # koszt jednostkowy produkcji normalnej [$/szt]
param a{T}   >= 0;    # maks. produkcja ponadwymiarowa [szt]
param o{T}   >= 0;    # koszt jednostkowy produkcji ponadwymiarowej [$/szt]
param d{T}   >= 0;    # popyt w okresie j [szt]

# Parametry globalne
param cap_norm    >= 0;  # maks. produkcja normalna w okresie [szt]
param store_cap   >= 0;  # maks. stan magazynu [szt]
param hold_cost   >= 0;  # koszt przechowywania 1 jednostki przez 1 okres [$/szt]
param start_stock >= 0;  # pocz¹tkowy stan magazynu [szt]

# Zmienne decyzyjne
var x{T} >= 0;   # produkcja normalna w okresie j
var y{T} >= 0;   # produkcja ponadwymiarowa w okresie j
var s{T} >= 0;   # zapas na koniec okresu j

# Funkcja celu: minimalizacja ca³kowitego kosztu
minimize TotalCost:
    # Koszty produkcji
    sum{t in T} ( c[t] * x[t] + o[t] * y[t] )
  + # Koszty magazynowania (tylko gdy przenosimy na kolejny okres)
    hold_cost * sum{t in T: t < K} s[t];

# Ograniczenia mocy produkcyjnych

s.t. NormalCapacity{t in T}:
    x[t] <= cap_norm;

s.t. ExtraCapacity{t in T}:
    y[t] <= a[t];

# Ograniczenie pojemnoœci magazynu
s.t. StorageCap{t in T}:
    s[t] <= store_cap;

# Równania bilansu zapasu

s.t. Balance1:
    start_stock + x[1] + y[1] = d[1] + s[1];

s.t. Balance{t in T: t >= 2}:
    s[t-1] + x[t] + y[t] = d[t] + s[t];

solve;

printf "Minimalny ³¹czny koszt produkcji i magazynowania: %g\n", TotalCost;

printf "\nOkres  x(normalna)  y(ponadwym.)  s(zapas koniec okresu)\n";
printf {t in T} "%3d   %10g     %10g       %10g\n",
                 t, x[t], y[t], s[t];

end;
