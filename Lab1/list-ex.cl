(*
    Laborator COOL.
*)

(*
    Exercițiul 1.

    Implementați funcția fibonacci, utilizând atât varianta recursivă,
    cât și cea iterativă.
*)
class Fibo {
    fibo_rec(n : Int) : Int {
        if n < 2 then
            n
        else
            fibo_rec(n - 1) + fibo_rec(n - 2)
        fi
    };

    fibo_iter(n : Int) : Int {
        let a : Int <- 0,
            b : Int <- 1,
            i : Int <- 0
        in
        {
            while 0 < n loop
                {
                    i <- a + b;
                    a <- b;
                    b <- i;
                    n <- n - 1;
                }
            pool;
            a;
        }
    };
};
    
(*
    Exercițiul 2.

    Pornind de la ierarhia de clase implementată la curs, aferentă listelor
    (găsiți clasele List și Cons mai jos), implementați următoarele funcții
    și testați-le. Este necesară definirea lor în clasa List și supradefinirea
    în clasa Cons.

    * append: întoarce o nouă listă rezultată prin concatenarea listei curente
        (self) cu lista dată ca parametru;
    * reverse: întoarce o nouă listă cu elementele în ordine inversă.

    Observați cerințele de întoarcere a unei *noi* liste. Operațiile
    se realizează nedistructiv, fără a altera listele existente. Amintiți-vă
    cum ați scrie axiomele pentru append și reverse într-un limbaj funcțional.
*)

(*
    Listă omogenă cu elemente de tip Int. Clasa List constituie rădăcina
    ierarhiei de clase reprezentând liste. O variabilă cu tipul static List
    poate desemna atât liste vide, cât și nevide. O instanță cu tipul dinamic
    List desemnează o listă vidă. O instanță cu tipul dinamic Cons denotă
    o listă nevidă.

    Adaptare după arhiva oficială de exemple a limbajului COOL.
*)
class List inherits IO {
    isEmpty() : Bool { true };

    -- 0, deși cod mort, este necesar pentru verificarea tipurilor
    hd() : Int { { abort(); 0; } };

    -- Similar pentru self
    tl() : List { { abort(); self; } };

    cons(h : Int) : Cons {
        new Cons.init(h, self)
    };

    append(list2 : List) : List {
        list2
    };

    reverse() : List {
        self
    };

    map(m : MapCons) : List {
        self
    };

    filter(f : Filter) : List {
        self
    };

    print() : IO { out_string("\n") };
};

(*
    În privința vizibilității, atributele sunt implicit protejate, iar metodele,
    publice.

    Atributele și metodele utilizează spații de nume diferite, motiv pentru care
    hd și tl reprezintă nume atât de atribute, cât și de metode.
*)
class Cons inherits List {
    hd : Int;
    tl : List;

    init(h : Int, t : List) : Cons {
        {
            hd <- h;
            tl <- t;
            self;
        }
    };

    -- Supradefinirea funcțiilor din clasa List
    isEmpty() : Bool { false };

    hd() : Int { hd };

    tl() : List { tl };

    print() : IO {
        {
            out_int(hd);
            out_string(" ");
            -- Mecanismul de dynamic dispatch asigură alegerea implementării
            -- corecte a metodei print.
            tl.print();
        }
    };

    append(list2 : List) : List {
        tl.append(list2).cons(hd)
    };

    reverse () : List {
        tl.reverse().append(new List.cons(hd))
    };

    map(m : MapCons) : List {
        m.apply(self)
    };

    filter(f : Filter) : List {
        f.apply(self)
    };
};

class Map inherits IO {
    apply(l : List) : List {
            l
    };
};

class MapCons inherits Map {
    apply(l : List) : List {
        if l.isEmpty() then l else apply(l.tl()).cons(l.hd() + 1) fi
        --apply(l.tl()).cons(l.hd() + 1)
    };
};

class MapCount inherits MapCons {
    count : Int <- 0;

    getCount() : Int {
        count
    };

    apply(l : List) : List {
        if l.isEmpty() then l
        else {
            count <- count + 1;
            out_int(count);
            out_string("\n");
            apply(l.tl()).cons(l.hd() + 1);
        } fi
    };   
};

class Filter inherits IO {

    isOK(e : Int) : Bool {
        if e = 1 then false else true fi 
    };

    apply(l : List) : List {
        if l.isEmpty() then new List
        else {        
            if not l.hd() = 1 then {
                apply(l.tl()).cons(l.hd());
            } else apply(l.tl()) fi;
        } fi
    };
};

(*
    Exercițiul 3.

    Scopul este implementarea unor mecanisme similare funcționalelor
    map și filter din limbajele funcționale. map aplică o funcție pe fiecare
    element, iar filter reține doar elementele care satisfac o anumită condiție.
    Ambele întorc o nouă listă.

    Definiți clasele schelet Map, respectiv Filter, care vor include unica
    metodă apply, având tipul potrivit în fiecare clasă, și implementare
    de formă.

    Pentru a defini o funcție utilă, care adună 1 la fiecare element al listei,
    definiți o subclasă a lui Map, cu implementarea corectă a metodei apply.

    În final, definiți în cadrul ierarhiei List-Cons o metodă map, care primește
    un parametru de tipul Map.

    Definiți o subclasă a subclasei lui Map de mai sus, care, pe lângă
    funcționalitatea existentă, de incrementare cu 1 a fiecărui element,
    contorizează intern și numărul de elemente prelucrate. Utilizați static
    dispatch pentru apelarea metodei de incrementare, deja definită.

    Repetați pentru clasa Filter, cu o implementare la alegere a metodei apply.
*)

-- Testați în main.
class Main inherits IO {
    main() : Object { {

        let list : List <- new List.cons(3).cons(2).cons(1),
            to_append : List <- new List.cons(4)
        in {
            out_string("Appended List: \n");
            list <- list.append(to_append);
            list.print();
            out_string("Reversed List: \n");
            list.reverse().print();
        };

        let list : List <- new List.cons(1).cons(2).cons(3),
                temp : List <- list,
                c: MapCount <- new MapCount
            in {
                out_string("Map list: \n");
                --list <- list.append(new List.cons(21).cons(100));
                --list <- list.reverse();
                --list <- list.map(new MapCons);
                list <- list.map(c);
                out_string("Count = ");
                out_int(c.getCount());
                out_string("\n");
                out_string("Filtered list: \n");
                list <- new List.cons(3).cons(2).cons(1);
                list <- list.filter(new Filter);
                list.print();
                --out_string("\n");
                -- -- Afișare utilizând o buclă while. Mecanismul de dynamic
                -- -- dispatch asigură alegerea implementării corecte a metodei
                -- -- isEmpty, din clasele List, respectiv Cons.
                -- while (not temp.isEmpty()) loop
                --     {
                --         out_int(temp.hd());
                --         out_string(" ");
                --         temp <- temp.tl();
                --     }
                -- pool;
                
                out_string("\n");

                -- Afișare utilizând metoda din clasele pe liste.
                list.print();
            };
        let fib : Fibo <- new Fibo,
            n : Int
        in
            {
                n <- 10;
                out_string("Fibonacci Recursive(");
                out_int(n);
                out_string(") = ");
                out_int(fib.fibo_rec(n));
                out_string("\n");
                out_string("Fibonacci Iterative(");
                out_int(n);
                out_string(") = ");
                out_int(fib.fibo_iter(n));
                out_string("\n");
            };
    } };
};