;; |0000
;; @x $2
;; |0100 
;; #0006 .x STZ2 
;; .x LDZ2 
;; #0007 
;; MUL2 

(module
    ;; Import print from JavaScript
    (import  "console" "log" (func  $log (param  i32 ))) ;; Import log function
    (global $x (mut i32) (i32.const 0))
    (func (export  "main") 
        i32.const 6  ;; #0006        
        global.set $x
        global.get $x ;; DUP2
        i32.const 7 
        i32.mul ;; MUL2

        call $log
    )
)
