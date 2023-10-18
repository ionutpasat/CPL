-- Din codul de la curs, sursa moodle
(*
   The class A2I provides integer-to-string and string-to-integer
conversion routines.  To use these routines, either inherit them
in the class where needed, have a dummy variable bound to
something of type A2I, or simpl write (new A2I).method(argument).
*)


(*
   c2i   Converts a 1-character string to an integer.  Aborts
         if the string is not "0" through "9"
*)
class A2I {

    c2i(char : String) : Int {
        if char = "0" then 0 else
        if char = "1" then 1 else
        if char = "2" then 2 else
        if char = "3" then 3 else
        if char = "4" then 4 else
        if char = "5" then 5 else
        if char = "6" then 6 else
        if char = "7" then 7 else
        if char = "8" then 8 else
        if char = "9" then 9 else
        { abort(); 0; }  -- the 0 is needed to satisfy the typchecker
        fi fi fi fi fi fi fi fi fi fi
    };

(*
  i2c is the inverse of c2i.
*)
    i2c(i : Int) : String {
        if i = 0 then "0" else
        if i = 1 then "1" else
        if i = 2 then "2" else
        if i = 3 then "3" else
        if i = 4 then "4" else
        if i = 5 then "5" else
        if i = 6 then "6" else
        if i = 7 then "7" else
        if i = 8 then "8" else
        if i = 9 then "9" else
        { abort(); ""; }  -- the "" is needed to satisfy the typchecker
        fi fi fi fi fi fi fi fi fi fi
    };

(*
  a2i converts an ASCII string into an integer.  The empty string
is converted to 0.  Signed and unsigned strings are handled.  The
method aborts if the string does not represent an integer.  Very
long strings of digits produce strange answers because of arithmetic 
overflow.

*)
    a2i(s : String) : Int {
        if s.length() = 0 then 0 else
            if s.substr(0,1) = "-" then ~a2i_aux(s.substr(1,s.length()-1)) else
                if s.substr(0,1) = "+" then a2i_aux(s.substr(1,s.length()-1)) else
                    a2i_aux(s)
        fi fi fi
    };

(*
 a2i_aux converts the usigned portion of the string.  As a programming
example, this method is written iteratively.
*)
    a2i_aux(s : String) : Int {
        (let int : Int <- 0 in  
            {    
                (let j : Int <- s.length() in
                    (let i : Int <- 0 in
                        while i < j loop
                        {
                            int <- int * 10 + c2i(s.substr(i,1));
                            i <- i + 1;
                        }
                        pool
                    )
                );
                int;
            }
        )
    };

(*
   i2a converts an integer to a string.  Positive and negative 
numbers are handled correctly.  
*)
   i2a(i : Int) : String {
    if i = 0 then "0" else 
        if 0 < i then i2a_aux(i) else
            "-".concat(i2a_aux(i * ~1)) 
        fi fi
   };
   
(*
   i2a_aux is an example using recursion.
*)      
   i2a_aux(i : Int) : String {
        if i = 0 then "" else 
            (let next : Int <- i / 10 in
                i2a_aux(next).concat(i2c(i - next * 10))
            )
        fi
   };

--    metoda pentru tipul bool
   b2a(b : Bool) : String {
        if b then "true" else "false" fi
   };

--    metoda pentru tipul bool
    a2b(s : String) : Bool {
        if s = "true" then
            true
        else
            false
        fi
    };
};

class List inherits IO {

    -- Verific daca lista e vida (in cazul asta da)
    isNil() : Bool { true };

    -- Returnez valoarea capatului listei
    head() : Object { {abort(); new Object;} };

    -- Returnez restul listei
    tail() : List { {abort(); new List;} };

    -- Returnez o lista de la un anumit index
    getListAtIndex(i : Int) : Object {{abort(); self;}};

    -- Returnez o lista noua din care elimin o lista la un anumit index
    removeListAtIndex(i : Int) : List {{abort(); self;}};

    -- Initializez un cons nou
    cons(obj : Object) : List { 
        (new Cons).init(obj, self)
     };

    -- Adaug o lista noua la finalul listei curente
    append(l : List) : List {
        l
    };

    -- Reversez lista curenta
    reverse() : List {
        self
    };

    -- Adaug un element nou in lista
    add(o : Object): List {
        cons(o)
    };

    -- Returnez un string nul
    toString():String {
        "" 
    };

    -- Adaug o lista noua la finalul listei curente
    merge(other : List): List {
        append(other)
    };

    -- Filtrez lista curenta folosind un anumit filtru
    filterBy(f : Filter): List {
        self
    };

    -- Sortez lista curenta folosind un anumit comparator
    sortBy(cmp : Comparator): List {
        self
    };

    sortByHelper(e : Object, cmp : Comparator) : Object {
        self
    };
};

class Cons inherits List {
    car : Object; -- capatul listei
    cdr : List; -- restul listei
    converter : A2I <- new A2I; 

    -- Verific daca lista e vida (in cazul asta nu)
    isNil() : Bool { false };

    -- Returnez valoarea capatului listei
    head() : Object { car };

    -- Returnez restul listei
    tail() : List { cdr };

    -- Returnez o lista de la un anumit index
    getListAtIndex(i : Int) : Object {
        if i = 0 then head()
        else tail().getListAtIndex(i - 1)
        fi
    };

    -- Returnez o lista noua din care elimin o lista la un anumit index
    removeListAtIndex(i : Int) : List {
        if i = 0 then cdr
        else new Cons.init(car, cdr.removeListAtIndex(i - 1))
        fi
    };

    -- Construiesc un cons
    init(head : Object, tail : List) : Cons {
        {
            car <- head;
            cdr <- tail;
            self;
        }
    };

    -- Adaug o lista noua la finalul listei curente
    append(l : List) : List {
        if l.isNil() then self
        else 
        let list : List <- new List in
            list <- tail().append(l).cons(head())
        fi
    };

    -- Reversez lista curenta
    reverse() : List {
        if isNil() then self
        else
        let list : List <- new List in
            list <- tail().reverse().append(list.cons(head()))
        fi
    };

    -- Filtrez lista curenta folosind un anumit filtru
    filterBy(f : Filter): List {
        let list: List <- new List in
            if f.filter(head()) then list <- tail().filterBy(f).cons(head())
            else list <- tail().filterBy(f)
            fi
    };

    -- Sortez lista curenta folosind un anumit comparator
    sortBy(c : Comparator) : List {
        let list : List <- new List in
            if cdr.isNil() then self
            else list <- new List.cons(sortByHelper(head(), c)).append(tail().sortBy(c))
            fi
    };

    sortByHelper(o : Object, c : Comparator) : Object {
        if c.compareTo(o, head()) < 0  then o
        else tail().sortByHelper(o, c)
        fi
    };

    -- Transform lista curenta si obiectele din ea intr-un string pentru a le putea afisa
    toString() : String
    {
        let result : String <- "",
            listCopy : List <- self in
        {
            while not listCopy.isNil() loop {
                case listCopy.head() of
                    type : IO => result <- result.concat("IO()");
                    type : Int => result <- result.concat("Int(").concat(converter.i2a(type)).concat(")");
                    type : String => result <- result.concat("String(").concat(type).concat(")");
                    type : Bool => result <- result.concat("Bool(").concat(converter.b2a(type)).concat(")");
                    type : Product => result <- result.concat(type.toString());
                    type : Rank => result <- result.concat(type.toString());
                    type : List => result <- result.concat(type.toString());
                esac;
                listCopy <- listCopy.tail();

                if not listCopy.isNil() then result <- result.concat(", ")
                else result <- result.concat("")
                fi;
            } pool;

            result;
        }
    };
};

class StringTokenizer inherits IO
{
    separator : String;
    string : String;
    stringLen : Int;
    currentPos : Int;

    init(separatorIn : String, stringIn : String) : StringTokenizer {
        {
            separator <- separatorIn;
            string <- stringIn;
            stringLen <- string.length();
            currentPos <- 0;
            self;
        }
    };

    -- Construiesc un string in care adaug caracter cu caracter pana la separator
    getCurrentToken() : String {
        let currToken : String <- "",
            currPos : Int <- currentPos in
        {
            while currPos < stringLen loop
            {
                if  not string.substr(currentPos, 1) = separator then
                    {
                        currToken <- currToken.concat(string.substr(currentPos, 1));
                        currentPos <- currentPos + 1;
                        currPos <- currentPos;
                    }
                else
                    {
                        currentPos <- currentPos + 1;
                        currPos <- stringLen;
                    }
                fi;
            }pool;

            currToken;
        }
    };
};(*******************************
 *** Classes Product-related ***
 *******************************)
 class Product {
    name : String;
    model : String;
    price : Int;

    init(n : String, m: String, p : Int): Product {{
        name <- n;
        model <- m;
        price <- p;
        self;
    }};

    getprice():Int{ price * 119 / 100 };

    toString():String {
        type_name().concat("(").concat(name).concat(",").concat(model).concat(")")
    };
};

class Edible inherits Product {
    -- VAT tax is lower for foods
    getprice():Int { price * 109 / 100 };
};

class Soda inherits Edible {
    -- sugar tax is 20 bani
    getprice():Int {price * 109 / 100 + 20};
};

class Coffee inherits Edible {
    -- this is technically poison for ants
    getprice():Int {price * 119 / 100};
};

class Laptop inherits Product {
    -- operating system cost included
    getprice():Int {price * 119 / 100 + 499};
};

class Router inherits Product {};

(****************************
 *** Classes Rank-related ***
 ****************************)
class Rank {
    name : String;

    init(n : String): Rank {
        {
            name <- n;
            self;
        }
    };

    toString():String {
        type_name().concat("(").concat(name).concat(")")
    };
};

class Private inherits Rank {};

class Corporal inherits Private {};

class Sergent inherits Corporal {};

class Officer inherits Sergent {};

class Filter {
    filter(o : Object):Bool {true};
};

class ProductFilter inherits Filter
{
    filter(o : Object): Bool {
        case o of
            type : Product => true;
            type : Object => false;
        esac
    };
};

class RankFilter inherits Filter
{
    filter(o : Object): Bool {
        case o of
            type : Rank => true;
            type : Object => false;
        esac
    };
};

class SamePriceFilter inherits Filter
{
    filter(o : Object): Bool {
        case o of
            type : Product => type.getprice() = type@Product.getprice();
            type : Object => false;
        esac
    };
};

class Comparator {
    compareTo(o1 : Object, o2 : Object):Int {0};
};

class PriceComparator inherits Comparator
{
    compareTo(o1 : Object, o2: Object) : Int {
        let firstPrice : Int,
            secondPrice : Int in
        {
            case o1 of
                type : Product => firstPrice <- type.getprice();
                type : Object => {0-1; abort();};
            esac;

            case o2 of
                type : Product => secondPrice <- type.getprice();
                type : Object => {0-1; abort();};
            esac;

            firstPrice - secondPrice;
        }
    };
};

class RankComparator inherits Comparator
{
    compareTo(o1 : Object, o2 : Object) : Int {
        let firstRank : Int,
            secondRank : Int in
        {
            case o1 of
                type : Private => firstRank <- 1;
                type : Corporal => firstRank <- 2;
                type : Sergent => firstRank <- 3;
                type : Officer => firstRank <- 4;
                type : Object => { firstRank <- 0-1; abort();};
            esac;
            
            case o2 of
                type : Private => secondRank <- 1;
                type : Corporal => secondRank <- 2;
                type : Sergent => secondRank <- 3;
                type : Officer => secondRank <- 4;
                type : Object => { secondRank <- 0-1; abort();};

            esac;

            firstRank - secondRank;
        }
    };
};

class AlphabeticComparator inherits Comparator
{
    compareTo(o1 : Object, o2 : Object) : Int {
        let result : Int <- 0-2 in
        {
            if o1.type_name() = "String" then
                if o2.type_name() = "String" then
                    result = compareStrs(o1, o2)
                else
                    abort()
                fi
            else
                abort()
            fi;

            result;
        }
    };

    compareStrs(s1 : Object, s2: Object) : Int {
        if s1 < s2 then
            0-1
        else 
            if s2 < s1 then
                1
            else
                0
            fi
        fi
    };
};

class Main inherits IO{
    lists : List <- new List;
    looping : Bool <- true;
    somestr : String;
    stringTokenizer : StringTokenizer <- new StringTokenizer;
    converter : A2I <- new A2I;

    -- help function
    help() : IO {
        {
            out_string("All available commands are the following (inputs between {} are optional):\n");
            out_string("1) help - get a list with all available commands\n");
            out_string("2) load - load a new list into memory\n");
            out_string("3) print {index} - print all lists or a specific one at {index}\n");
            out_string("4) merge index1 index2 - merges the two lists at the given indexes\n");
            out_string("5) filterBy index {ProductFilter,RankFilter,SamePriceFilter} - filters the list at the given index with the specified filter\n");
            out_string("6) sortBy index {PriceComparator,RankComparator,AlphabeticComparator} {ascendent,descendent} - sorts the list at the given index in the specified order\n\n");
            out_string("7) exit - stop the program (Anything else will end the program).\n");
        }
    };

    -- load function
    load() : Object {
        let flagLooping : Bool <- true,
            auxList : List <- new List,
            token : String <- "",
            line : String,
            objType : Object in
        {
            while flagLooping loop
            {
                line <- in_string();
            
                stringTokenizer <- stringTokenizer.init(" ", line);
                token <- stringTokenizer.getCurrentToken();

                if token = "Soda" then
                {
                    objType <- new Soda.init(stringTokenizer.getCurrentToken(), stringTokenizer.getCurrentToken(), converter.a2i(stringTokenizer.getCurrentToken()));
                    auxList <- auxList.add(objType);
                }
                else if token = "Coffee" then
                {
                    objType <- new Coffee.init(stringTokenizer.getCurrentToken(), stringTokenizer.getCurrentToken(), converter.a2i(stringTokenizer.getCurrentToken()));
                    auxList <- auxList.add(objType);
                }
                else if token = "Laptop" then
                {
                    objType <- new Laptop.init(stringTokenizer.getCurrentToken(), stringTokenizer.getCurrentToken(), converter.a2i(stringTokenizer.getCurrentToken()));
                    auxList <- auxList.add(objType);
                }
                else if token = "Router" then
                {
                    objType <- new Router.init(stringTokenizer.getCurrentToken(), stringTokenizer.getCurrentToken(), converter.a2i(stringTokenizer.getCurrentToken()));
                    auxList <- auxList.add(objType);
                }
                else if token = "Private" then
                {
                    objType <- new Private.init(stringTokenizer.getCurrentToken());
                    auxList <- auxList.add(objType);
                }
                else if token = "Corporal" then
                {
                    objType <- new Corporal.init(stringTokenizer.getCurrentToken());
                    auxList <- auxList.add(objType);
                }
                else if token = "Sergent" then
                {
                    objType <- new Sergent.init(stringTokenizer.getCurrentToken());
                    auxList <- auxList.add(objType);
                }
                else if token = "Officer" then
                {
                    objType <- new Officer.init(stringTokenizer.getCurrentToken());
                    auxList <- auxList.add(objType);
                }
                else if token = "IO" then
                {
                    objType <- new IO;
                    auxList <- auxList.add(objType);
                }
                else if token = "Int" then
                {
                    objType <- converter.a2i(stringTokenizer.getCurrentToken());
                    auxList <- auxList.add(objType);
                }
                else if token = "String" then
                {
                    objType <- stringTokenizer.getCurrentToken();
                    auxList <- auxList.add(objType);
                }
                else if token = "Bool" then
                {
                    objType <- converter.a2b(stringTokenizer.getCurrentToken());
                    auxList <- auxList.add(objType);
                } 
                else if token = "END" then 
                {
                    flagLooping <- false;
                }
                else 
                {
                    out_string("Invalid input! Try again!\n");
                    abort();
                }
                fi fi fi fi fi fi fi fi fi fi fi fi fi;
            }
            pool;

            lists <- lists.add(new List.cons(auxList.reverse()));
        }
    };

    -- print function
    print(idx : String) : Object {
        let i : Int <- 1,
            newL : List,
            auxList : List <- lists.reverse(),
            listCopy : List <- lists.reverse() in
        {
            if not auxList.tail().isNil() then
            {
                if not idx = "" then 
                {
                    newL <- new List.cons(listCopy.getListAtIndex(converter.a2i(idx)- 1));
                    out_string("[ ").out_string(newL.toString()).out_string(" ]\n");
                }
                else
                while not listCopy.isNil() loop
                {
                    out_int(i).out_string(": [ ").out_string(new List.cons(listCopy.head()).toString()).out_string(" ]\n");
                    listCopy <- listCopy.tail();
                    i <- i + 1;
                } pool
                fi;
            }
            else out_string("[ ").out_string(new List.cons(listCopy.head()).toString()).out_string(" ]\n")
            fi;
        }
    };

    --merge function
    merge(idx1 : String, idx2 : String ) : List {
        let newL1 : List,
            newL2 : List,
            auxList : List,
            listCopy : List <- lists.reverse() in
        {
            -- preiau listele de la indecsii primiti ca param
            newL1 <- new List.cons(listCopy.getListAtIndex(converter.a2i(idx1)- 1));
            newL2 <- new List.cons(listCopy.getListAtIndex(converter.a2i(idx2)- 1));
            -- unesc cele doua liste obtinue
            auxList <- new List.cons(newL1).append(newL2).reverse();
            -- elimin listele de la indescii utilizati
            listCopy <- listCopy.removeListAtIndex((converter.a2i(idx2) -1));
            listCopy <- listCopy.removeListAtIndex((converter.a2i(idx1) -1));
            -- updatez lista de liste cu noua lista formata
            lists <- listCopy.reverse();
            lists <- lists.add(new List.cons(auxList.reverse()));
        }
    };

    -- filterBy function
    filterBy(idx: String, filter: String): List {
        let newL : Object,
            auxList : List,
            listCopy : List <- lists.reverse() in
        {
            -- preiau lista de la indexul primit ca parametru
            newL <- listCopy.getListAtIndex(converter.a2i(idx)- 1);
            case newL of
                type : List =>
                {
                    case type.head() of
                        type2 : List =>
                        {
                            -- aplic filtrul pe lista preluata
                            if filter = "ProductFilter" then auxList <- type2.filterBy(new ProductFilter)
                            else if filter = "RankFilter" then auxList <- type2.filterBy(new RankFilter)
                            else if filter = "SamePriceFilter" then auxList <- type2.filterBy(new SamePriceFilter)
                            else auxList <- new List
                            fi fi fi;
                        };
                    esac;
                };
            esac;

            -- elimin lista de la indexul primit ca parametru
            listCopy <- listCopy.removeListAtIndex((converter.a2i(idx) -1));
            -- updatez lista de liste cu noua lista formata
            listCopy <- listCopy.add(new List.cons(auxList));
            lists <- listCopy.reverse();
        }
    };

    -- sortBy function
    sortBy(idx: String, comparator: String, order: String): List {
        let newL : Object,
            auxList : List,
            listCopy : List <- lists.reverse() in
        {
            -- preiau lista de la indexul primit ca parametru
            newL <- listCopy.getListAtIndex(converter.a2i(idx)- 1);
            case newL of
                type : List =>
                {
                    case type.head() of
                        type2 : List =>
                        {
                            -- fac sortarea pe lista preluata
                            if comparator = "PriceComparator" then auxList <- type2.sortBy(new PriceComparator)
                            else if comparator = "RankComparator" then auxList <- type2.sortBy(new RankComparator)
                            else if comparator = "AlphabeticComparator" then auxList <- type2.sortBy(new AlphabeticComparator)
                            else auxList <- new List
                            fi fi fi;
                        };
                    esac;
                };
            esac;
        
            -- elimin lista de la indexul primit ca parametru si o adaug pe cea sortata in functie de ordine
            listCopy <- listCopy.removeListAtIndex((converter.a2i(idx) -1));

            if order = "ascendent" then listCopy <- listCopy.add(new List.cons(auxList))
            else listCopy <- listCopy.add(new List.cons(auxList.reverse()))
            fi;

            -- updatez lista de liste cu noua lista formata
            lists <- listCopy.reverse();
        }       
    };

    -- main function
    main():Object {
        let token : String <- "" in
        {
            load();
            while looping loop
            {
                somestr <- in_string();
                stringTokenizer <- stringTokenizer.init(" ", somestr);
                token <- stringTokenizer.getCurrentToken();

                if token = "help" then help()
                else if token = "load" then load()
                else if token = "print" then print(stringTokenizer.getCurrentToken()) 
                else if token = "merge" then merge(stringTokenizer.getCurrentToken(), stringTokenizer.getCurrentToken())
                else if token = "filterBy" then filterBy(stringTokenizer.getCurrentToken(), stringTokenizer.getCurrentToken())
                else if token = "sortBy" then sortBy(stringTokenizer.getCurrentToken(), stringTokenizer.getCurrentToken(), stringTokenizer.getCurrentToken())
                else looping <- false
                fi fi fi fi fi fi;
            } pool;
        }
    };
};