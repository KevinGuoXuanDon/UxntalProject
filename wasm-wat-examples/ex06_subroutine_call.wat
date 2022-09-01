

(module
(import  "console" "log" (func  $log (param  i32 ))) ;; Import log function, comment to run in browser
(func (export "main") 
;; (result i32) ;; uncomment to run in browser
    i32.const 4
    i32.const 3
    call $sum_sq
    i32.const 2
    i32.const 3
    call $sum_sq    
    i32.add
    call $log ;; comment to run in browser
)

(func $sum_sq (param $x i32) (param $y i32) (result i32)
    (local $_a i32) (local $_b i32) (local $_c i32) ;; local vars for stack manipulation
    local.get $x local.get $y ;; put x and y on the stack
    local.tee $_c local.get $_c ;; DUP2 
    i32.mul ;; so we have now x y*y
    local.set $_c local.set $_b local.get $_c local.get $_b ;; SWP2
    local.tee $_c local.get $_c ;; DUP2
    i32.mul ;; so we have y*y x*x
    i32.add
    return
)
)
;; DUP: local.tee $c local.get $c
;; SWP: local.set $c local.set $b local.get $c local.get $b
