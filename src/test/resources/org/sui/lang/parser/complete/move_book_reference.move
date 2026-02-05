// Comprehensive Move Book Reference compliance test
// https://move-book.com/reference/

// 1. Modules
module 0x1::move_book_reference {

    // 2.1-2.6 Primitive Types: Integers, Bool, Address, Vector, References, Tuples
    const ZERO: u64 = 0;
    const ADDR: address = @0x42;

    public fun primitives() {
        let _i: u64 = 42;
        let _b: bool = true;
        let _a: address = @0x1;
        let _v: vector<u8> = vector[];
        let _r: &u64 = &0;
        let _rm: &mut u64 = &mut 1;
        let _t: (u64, bool) = (0, false);
        let _u: () = ();
    }

    // 3. Local Variables and Scopes
    public fun locals() {
        let mut x = 0;
        let (mut a, mut b): (u8, u64) = (0, 1);
        let () = ();
        (a, b) = (1, 2);
        x = 1;
    }

    // 4. Equality
    public fun equality() {
        let a = 1 == 1;
        let b = 1 != 2;
    }

    // 5. Abort and Assert
    public fun abort_assert() {
        if (false) abort 0;
        assert!(true, 0);
    }

    // 6. Control Flow
    public fun control_flow() {
        let x = if (true) 1 else 2;
        loop { break 0 };
        while (false) {};
        for (i in vector[1, 2, 3]) { i };
    }

    // 6.3 Labeled Control Flow
    public fun labeled_loops() {
        'outer: loop { break 'outer 1 };
        'outer: while (false) { continue 'outer };
    }

    // 6.4 Labeled Blocks
    public fun labeled_blocks() {
        let x = 'a: { if (true) { return 'a 1 }; 0 };
    }

    // 6.4 Pattern Matching
    public fun match_expr() {
        match (0) { 1 => 2, 2 => 3, x => x };
        match (true) { true => 1, false => 0 };
    }

    // 7. Functions
    fun plain_fn(): u64 { 0 }
    public fun multi_return(): (u64, bool, address) { (0, false, @0x42) }

    // 7.1 Macros
    macro fun apply($f: |u64| -> u64, $x: u64): u64 { $f($x) }

    // 8. Structs
    struct Simple { val: u64 }
    struct Tuple(u64, bool);

    // 9. Enums
    enum Option<T> { None, Some(T) }

    // 10. Constants
    const ONE: u64 = 1;

    // 11. Generics
    fun generic<T: copy + drop>(x: T): T { x }

    // 12. Abilities
    struct WithAbilities has copy, drop, store { x: u64 }

    // 14. Method Syntax
    public fun method_syntax(s: &Simple): u64 { s.val }

    // 15. Index Syntax
    public fun index_syntax() {
        let v = vector[1u8, 2u8, 3u8];
        let _x = v[0];
    }
}
