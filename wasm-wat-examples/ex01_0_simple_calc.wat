(module
    ;; Import print from JavaScript
    (import  "console" "log" (func  $log (param  i32 ))) ;; Import log function

    (func (export  "main")
        i32.const 6 ;; #0006  
        i32.const 7 ;; #0007
        i32.mul ;; MUL2
        call $log ;; DEO #18
    )
)