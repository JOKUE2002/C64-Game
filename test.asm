!cpu 6502
!to "build/main.prg",cbm    ; output file

;============================================================
; BASIC loader with start address $c000
;============================================================

* = $0801                               ; BASIC start address (#2049)
!byte $0d,$08,$dc,$07,$9e,$20,$34,$39   ; BASIC loader to start at $c000...
!byte $31,$35,$32,$00,$00,$00           ; puts BASIC line 2012 SYS 49152

* = $3a00								; charset start address (0x3800 + 64 * 8)
!bin "charset.bin"						; charset data (binary file. open with "hexdump -C charset.bin")

* = $c000     				            ; start address for 6502 code

;============================================================
; VARIABLES
;============================================================



;============================================================
; START OF CODE
;============================================================

jsr init_screen

jsr draw_test
jsr change_charset
jsr init_text


jmp * ;end of code

;============================================================
; END OF CODE
;============================================================





;============================================================
; SUBROUTINES
;============================================================

init_screen		ldx #$00
				stx $d021
				stx $d020

clear			lda #$20
				sta $0400,x
				sta $0500,x
				sta $0600,x
				sta $06e8,x
				lda #$00
				sta $d800,x
				sta $d900,x
				sta $da00,x
				sta $dae8,x
				inx
				bne clear

				rts

draw_test		lda #$01
				sta $d800
				lda #$40
				sta $0400
				rts

line1           !scr "@abcdefghijklmnopqrstuvwxyz[ ]   ! #$%&'"
line2			!scr "()*+,-./0123456789:;<=>?                "

init_text  		ldx #$00
				lda #$00
loop_text  		txa
				sta $0400,x
           		
           		lda #$01
           		sta $d800,x
           
           		inx
           		bne loop_text
           		rts
 	
; This routine copies the first 64 chars from the default charset to a new location
; here the second half (char $40 to $ff) are replaced with the iconset
change_charset 	sei
				lda $d018
           		ora #$0e         ; set charset location to $3800 ($d018 1st to 3rd bits = 1)
           		sta $d018
           		ldx #$00

   				lda #$33 		 ; make the CPU see the Character Generator ROM...
        		sta $01     	 ; ...at $D000 by storing %00110011 into location $01

copy_charset	lda $d000,x
				sta $3800,x
				lda $d100,x
				sta $3900,x
				inx 
           		bne copy_charset

           		lda $d016
           		and #$ef         ; turn off multicolor ($d016 4th bit = 0)
           		sta $d016

           		lda #$37    	 ; switch in I/O mapped registers again...
        		sta $01     	 ; ... with %00110111 so CPU can see them

        		cli

           		rts



