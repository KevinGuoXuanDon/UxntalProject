;; @x $2
;; |0100
;; #0006 .x STZ2
;; .x LDZ2
;; .x LDZ2 INC2 MUL2

(module
    ;; Import print from JavaScript
    (import  "console" "log" (func  $log (param  i32 ))) ;; Import log function
    (global $x (mut i32) (i32.const 0))
    (func (export  "main") 
        ;; store 6 in register x
        i32.const 6  global.set $x
        ;; load 6 from x
        global.get $x ;; #0006
        ;; load 6 from x, add 1, multiply both
        global.get $x ;; #0006
        i32.const 1 i32.add 
        i32.mul ;; MUL2

        call $log
    )
)
