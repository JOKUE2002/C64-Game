*=$0801
.byte $0c,$08,$e2,$07,$9e,$20,$32,$30,$36,$32,$00,$00,$00

lda #$41
ldx #$ff

loop:
  sta $03ff,X
  dex
  bne loop
  rts
