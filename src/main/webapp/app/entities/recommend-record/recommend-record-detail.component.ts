import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRecommendRecord } from 'app/shared/model/recommend-record.model';

@Component({
    selector: 'jhi-recommend-record-detail',
    templateUrl: './recommend-record-detail.component.html'
})
export class RecommendRecordDetailComponent implements OnInit {
    recommendRecord: IRecommendRecord;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ recommendRecord }) => {
            this.recommendRecord = recommendRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
