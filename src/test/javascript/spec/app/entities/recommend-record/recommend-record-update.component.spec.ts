/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { RecommendRecordUpdateComponent } from 'app/entities/recommend-record/recommend-record-update.component';
import { RecommendRecordService } from 'app/entities/recommend-record/recommend-record.service';
import { RecommendRecord } from 'app/shared/model/recommend-record.model';

describe('Component Tests', () => {
    describe('RecommendRecord Management Update Component', () => {
        let comp: RecommendRecordUpdateComponent;
        let fixture: ComponentFixture<RecommendRecordUpdateComponent>;
        let service: RecommendRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RecommendRecordUpdateComponent]
            })
                .overrideTemplate(RecommendRecordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RecommendRecordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RecommendRecordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RecommendRecord(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.recommendRecord = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RecommendRecord();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.recommendRecord = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
