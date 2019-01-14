import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRecommendRecord } from 'app/shared/model/recommend-record.model';

type EntityResponseType = HttpResponse<IRecommendRecord>;
type EntityArrayResponseType = HttpResponse<IRecommendRecord[]>;

@Injectable({ providedIn: 'root' })
export class RecommendRecordService {
    public resourceUrl = SERVER_API_URL + 'api/recommend-records';

    constructor(protected http: HttpClient) {}

    create(recommendRecord: IRecommendRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(recommendRecord);
        return this.http
            .post<IRecommendRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(recommendRecord: IRecommendRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(recommendRecord);
        return this.http
            .put<IRecommendRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRecommendRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRecommendRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(recommendRecord: IRecommendRecord): IRecommendRecord {
        const copy: IRecommendRecord = Object.assign({}, recommendRecord, {
            createTime:
                recommendRecord.createTime != null && recommendRecord.createTime.isValid() ? recommendRecord.createTime.toJSON() : null,
            lastModifyTime:
                recommendRecord.lastModifyTime != null && recommendRecord.lastModifyTime.isValid()
                    ? recommendRecord.lastModifyTime.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.lastModifyTime = res.body.lastModifyTime != null ? moment(res.body.lastModifyTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((recommendRecord: IRecommendRecord) => {
                recommendRecord.createTime = recommendRecord.createTime != null ? moment(recommendRecord.createTime) : null;
                recommendRecord.lastModifyTime = recommendRecord.lastModifyTime != null ? moment(recommendRecord.lastModifyTime) : null;
            });
        }
        return res;
    }
}
