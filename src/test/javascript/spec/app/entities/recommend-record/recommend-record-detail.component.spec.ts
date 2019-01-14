/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { RecommendRecordDetailComponent } from 'app/entities/recommend-record/recommend-record-detail.component';
import { RecommendRecord } from 'app/shared/model/recommend-record.model';

describe('Component Tests', () => {
    describe('RecommendRecord Management Detail Component', () => {
        let comp: RecommendRecordDetailComponent;
        let fixture: ComponentFixture<RecommendRecordDetailComponent>;
        const route = ({ data: of({ recommendRecord: new RecommendRecord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RecommendRecordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RecommendRecordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RecommendRecordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.recommendRecord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
