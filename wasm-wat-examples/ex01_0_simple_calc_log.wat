
(module
    ;; Import print from JavaScript
    (import  "printMod" "print" (func  $print (param  i32 ))) ;; Import log function

    (func (export  "main")
        i32.const 6  
        i32.const 7
        i32.mul ;; 42 
        call $print
    )
)