
(module
    ;; Import print from JavaScript
    (import  "console" "log" (func  $log (param  i32 ))) ;; Import log function
;; DUP2 ( duplicate, so there are now two values on the stack, both 6 )
;; INC2 ( increment the value on the top of the stack, so 6+1 )
;; MUL2 ( multiply the value on the top of the stack, 6, with the value below that, 7 )

    (func (export  "main") 
        (local $a i32)
        (local $b i32)
        (local $c i32)
        i32.const 6  ;; #0006        
        local.tee $c local.get $c ;; DUP2
        i32.const 1 i32.add ;; INC2
        i32.mul ;; MUL2F

        call $log
    )
)
