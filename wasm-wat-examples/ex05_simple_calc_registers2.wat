;; @x1 $2
;; @x2 $2
;; |0100
;; #0006 .x1 STZ2
;; .x1 LDZ2 INC2 .x2 STZ2
;; .x1 LDZ2 
;; .x2 LDZ2
;; MUL2 

(module
    ;; Import print from JavaScript
    (import  "console" "log" (func  $log (param  i32 ))) ;; Import log function
    (global $x1 (mut i32) (i32.const 0))
    (global $x2 (mut i32) (i32.const 0))
    (func (export  "main") 
        ;; store 6 in register x
        i32.const 6  global.set $x1
        ;; load 6 from x
        global.get $x 
        i32.const 1 i32.add 
        global.set $x2
        ;; load 6 from x, add 1, multiply both
        global.get $x1 ;; #0006
        global.get $x2 ;; #0006
        i32.mul ;; MUL2

        call $log
    )
)
