# Transportation – paliwo: firmy -> lotniska

set I;               # lotniska
set J;               # firmy paliwowe

param demand{I} >= 0;    # zapotrzebowanie lotnisk
param supply{J} >= 0;    # maks. mo¿liwoœci dostaw firm
param cost{I, J} >= 0;   # koszt 1 galonu od firmy j do lotniska i

var x{I, J} >= 0;        # iloœæ paliwa wys³ana z j do i (galony)

# Funkcja celu – minimalizacja ³¹cznego kosztu
minimize Total_Cost:
    sum{i in I, j in J} cost[i,j] * x[i,j];

# Ka¿de lotnisko musi dostaæ wymagan¹ iloœæ paliwa
s.t. Demand_Balance{i in I}:
    sum{j in J} x[i,j] = demand[i];

# Dostawy firm nie mog¹ przekroczyæ ich mo¿liwoœci
s.t. Supply_Limit{j in J}:
    sum{i in I} x[i,j] <= supply[j];

solve;

printf "Minimalny koszt = %g\n", Total_Cost;

printf "\nOptymalny plan dostaw (x[i,j] > 0):\n";
printf {i in I, j in J: x[i,j] > 0} "Lotnisko %s, Firma %s : %g\n",
                                   i, j, x[i,j];

end;
