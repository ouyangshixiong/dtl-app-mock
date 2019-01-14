/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { RealnameInfoUpdateComponent } from 'app/entities/realname-info/realname-info-update.component';
import { RealnameInfoService } from 'app/entities/realname-info/realname-info.service';
import { RealnameInfo } from 'app/shared/model/realname-info.model';

describe('Component Tests', () => {
    describe('RealnameInfo Management Update Component', () => {
        let comp: RealnameInfoUpdateComponent;
        let fixture: ComponentFixture<RealnameInfoUpdateComponent>;
        let service: RealnameInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RealnameInfoUpdateComponent]
            })
                .overrideTemplate(RealnameInfoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RealnameInfoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RealnameInfoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new RealnameInfo(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.realnameInfo = entity;
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
                    const entity = new RealnameInfo();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.realnameInfo = entity;
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
