/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { RecommendRecordComponent } from 'app/entities/recommend-record/recommend-record.component';
import { RecommendRecordService } from 'app/entities/recommend-record/recommend-record.service';
import { RecommendRecord } from 'app/shared/model/recommend-record.model';

describe('Component Tests', () => {
    describe('RecommendRecord Management Component', () => {
        let comp: RecommendRecordComponent;
        let fixture: ComponentFixture<RecommendRecordComponent>;
        let service: RecommendRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RecommendRecordComponent],
                providers: []
            })
                .overrideTemplate(RecommendRecordComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RecommendRecordComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RecommendRecordService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RecommendRecord(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.recommendRecords[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
