import { Component, ElementRef, Input, OnInit, Renderer2 } from '@angular/core';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';

@Component({
  selector: 'fitness-program-card',
  templateUrl: './fitness-program-card.component.html',
  styleUrls: ['./fitness-program-card.component.css']
})
export class FitnessProgramCardComponent implements OnInit{
  @Input()
  fitnessProgram!: FitnessProgram;

  constructor(private el: ElementRef,
    private renderer: Renderer2){}

  ngOnInit(): void {}

  setVisible(visible: boolean) {
    let value: string = visible ? '' : 'none'; 
    this.renderer.setStyle(this.el.nativeElement, 'display', value);
  }

}
