// Method syntax (https://move-book.com/reference/method-syntax)
module 0x1::cup {
    public struct Cup<T> has copy, drop, store { val: T }

    public fun cup_borrow<T>(c: &Cup<T>): &T {
        &c.val
    }

    public fun cup_value<T>(c: Cup<T>): T {
        let Cup { val: t } = c;
        t
    }

    public fun cup_swap<T: drop>(c: &mut Cup<T>, t: T) {
        c.val = t;
    }
}

module 0x1::example {
    use 0x1::cup::{Self, Cup};

    // Module-level use fun aliases
    use fun 0x1::cup::cup_borrow as Cup.borrow;
    use fun 0x1::cup::cup_value as Cup.value;
    use fun 0x1::cup::cup_swap as Cup.set;

    fun example(c: &mut Cup<u64>) {
        let _ = c.borrow();
        let v = c.value();
        c.set(v * 2);
    }

    fun block_scoped_use_fun(c: Cup<u64>) {
        use fun 0x1::cup::cup_value as Cup.val;
        let v = c.val();
    }

    fun chained_methods(c: Cup<Cup<u64>>) {
        let inner = c.value();
        let v = inner.borrow();
    }
}
