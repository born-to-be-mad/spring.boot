import { Component, OnInit } from '@angular/core';
import {CardService} from "../service/card.service";
import {Card} from "../model/card";

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.css']
})
export class CardListComponent implements OnInit {

  cards: Card[];

  constructor(private dataService: CardService) {
  }

  ngOnInit() {
    this.dataService.findAll().subscribe(data => {
      this.cards = data;
    });
  }

}
