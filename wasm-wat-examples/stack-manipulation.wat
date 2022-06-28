;; NIP: i32.const 1 select
;; POP: drop ;; or i32.const 0 select
;; SWP: local.set $c local.set $b local.get $c local.get $b 
;; DUP: local.tee $c local.get $c
;; ROT: local.set $c local.set $b local.set $a local.get $b local.get $c local.get $a
;; OVR: local.set $c local.tee $b local.get $c local.get $b