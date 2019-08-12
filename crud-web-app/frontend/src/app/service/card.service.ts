import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Card} from "../model/card";

@Injectable()
export class CardService {

  private url: string;

  constructor(private http: HttpClient) {
    this.url = '/api/cards';
  }

  public findAll(): Observable<Card[]> {
    return this.http.get<Card[]>(this.url);
  }
}
